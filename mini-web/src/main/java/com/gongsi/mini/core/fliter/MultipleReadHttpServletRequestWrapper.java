package com.gongsi.mini.core.fliter;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 */
public class MultipleReadHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(MultipleReadHttpServletRequestWrapper.class);

    private HttpServletRequest req;
    private byte[] contentData;
    private HashMap<String, String[]> parameters;

    public MultipleReadHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        Ensure.that(request).isNotNull("HttpServletRequest不能为空");
        req = request;
    }

    private void parseRequest() throws IOException {
        if (contentData != null) {
            return;
        }
        contentData = extractContentData();
        convertContentDataToParameters(contentData);
    }

    /**
     * 读取流
     *
     * @return
     * @throws IOException
     */
    private byte[] extractContentData() throws IOException {
        InputStream is = req.getInputStream();
        return IOUtils.toByteArray(is);
    }

    /**
     * 把contentData转换为parameters
     *
     * @param data
     * @throws UnsupportedEncodingException
     */
    private void convertContentDataToParameters(byte[] data) throws UnsupportedEncodingException {
        String enc = req.getCharacterEncoding();
        if (enc == null)
            enc = "UTF-8";
        String s = new String(data, enc), name, value;
        StringTokenizer st = new StringTokenizer(s, "&");
        int i;
        HashMap<String, LinkedList<String>> mapA = new HashMap<>(data.length * 2);
        LinkedList<String> paramList;
        boolean decode = req.getContentType() != null && "application/x-www-form-urlencoded".equals(req.getContentType());
        while (st.hasMoreTokens()) {
            s = st.nextToken();
            i = s.indexOf('=');
            if (i > 0 && s.length() > i + 1) {
                name = s.substring(0, i);
                value = s.substring(i + 1);
                if (decode) {
                    try {
                        name = URLDecoder.decode(name, StandardCharsets.UTF_8.name());
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    try {
                        value = URLDecoder.decode(value, StandardCharsets.UTF_8.name());
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
                paramList = mapA.get(name);
                if (CollectionUtils.isEmpty(paramList)) {
                    paramList = new LinkedList<>();
                    mapA.put(name, paramList);
                }
                paramList.add(value);
            }
        }
        HashMap<String, String[]> map = new HashMap<>(mapA.size() * 2);
        for (Map.Entry<String, LinkedList<String>> entry : mapA.entrySet()) {
            paramList = entry.getValue();
            map.put(entry.getKey(), paramList.toArray(new String[paramList.size()]));
        }
        //handle queryString
        if (StringUtils.isNotEmpty(req.getQueryString())) {
            String[] perQueryParameters = req.getQueryString().split("&");
            LinkedList<String> perList = new LinkedList<>();
            for (String per : perQueryParameters) {
                String[] inner = per.split("=");
                perList.add(inner[1]);
                map.put(inner[0], perList.toArray(new String[perList.size()]));
                perList.clear();
            }
        }
        parameters = map;
    }

    /**
     * This method is safe to call multiple times.
     * Calling it will not interfere with getParameterXXX() or getReader().
     * Every time a new ServletInputStream is returned that reads data from the begining.
     *
     * @return A new ServletInputStream.
     */
    public ServletInputStream getInputStream() throws IOException {
        parseRequest();
        return new ServletInputStreamWrapper(contentData);
    }

    /**
     * This method is safe to call multiple times.
     * Calling it will not interfere with getParameterXXX() or getInputStream().
     * Every time a new BufferedReader is returned that reads data from the begining.
     *
     * @return A new BufferedReader with the wrapped request's character encoding (or UTF-8 if null).
     */
    public BufferedReader getReader() throws IOException {
        parseRequest();
        String enc = req.getCharacterEncoding();
        if (enc == null)
            enc = "UTF-8";
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(contentData), enc));
    }


    /**
     * This method is safe to use multiple times.
     * Changing the returned map or the array of any of the map's values will not
     * interfere with this class operation.
     *
     * @return The clonned parameters map.
     */
    public HashMap<String, String[]> getParameters() {
        HashMap<String, String[]> map = new HashMap<>(parameters.size() * 2);
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            map.put(entry.getKey(), entry.getValue().clone());
        }
        return map;
    }

    /**
     * This method is safe to execute multiple times.
     *
     * @see javax.servlet.ServletRequest#getParameter(String)
     */
    public String getParameter(String name) {
        try {
            parseRequest();
        } catch (IOException e) {
            throw new BusinessException("Cannot parse the request!" + e);
        }
        String[] values = parameters.get(name);
        if (values == null || values.length == 0)
            return null;
        return values[0];
    }

    /**
     * This method is safe.
     *
     * @see {@link #getParameters()}
     * @see javax.servlet.ServletRequest#getParameterMap()
     */
    @SuppressWarnings("unchecked")
    public Map<String, String[]> getParameterMap() {
        try {
            parseRequest();
        } catch (IOException e) {
            throw new BusinessException("Cannot parse the request!" + e);
        }
        return getParameters();
    }

    /**
     * This method is safe to execute multiple times.
     *
     * @see javax.servlet.ServletRequest#getParameterNames()
     */
    @SuppressWarnings("unchecked")
    public Enumeration<String> getParameterNames() {
        try {
            parseRequest();
        } catch (IOException e) {
            throw new BusinessException("Cannot parse the request!" + e);
        }
        return new Enumeration<String>() {
            private String[] arr = getParameters().keySet().toArray(new String[0]);
            private int idx = 0;

            public boolean hasMoreElements() {
                return idx < arr.length;
            }

            public String nextElement() {
                return arr[idx++];
            }

        };
    }

    /**
     * This method is safe to execute multiple times.
     * Changing the returned array will not interfere with this class operation.
     *
     * @see javax.servlet.ServletRequest#getParameterValues(String)
     */
    public String[] getParameterValues(String name) {
        try {
            parseRequest();
        } catch (IOException e) {
            throw new BusinessException("Cannot parse the request!" + e);
        }
        String[] arr = parameters.get(name);
        if (arr == null)
            return null;
        return arr.clone();
    }

    private static class ServletInputStreamWrapper extends ServletInputStream {

        private byte[] data;
        private int idx = 0;

        ServletInputStreamWrapper(byte[] data) {
            if (data == null)
                data = new byte[0];
            this.data = data;
        }

        @Override
        public int read() throws IOException {
            if (idx == data.length)
                return -1;
            return data[idx++];
        }
        public boolean isFinished() {
            return this.idx == this.data.length;
        }

        public boolean isReady() {
            return this.data.length > 0;
        }

        public void setReadListener(ReadListener readListener) {
        }
    }

}
