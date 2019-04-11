package com.gongsi.mini.services.stock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by wuyu on 2019/4/11.
 */
@Slf4j
@Service
public class EmailService {

    public void send(String content) throws Exception {
        Properties prop=new Properties();
        prop.put("mail.host","smtp.163.com" );
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", true);
        //1.创建sesssion
        Session session=Session.getInstance(prop);
        //开启session的调试模式，可以查看当前邮件发送状态
        session.setDebug(true);
        //2.通过session获取Transport对象（发送邮件的核心API）
        Transport ts = session.getTransport();
        //3.通过邮件用户名密码链接
        ts.connect("scuwuyu", "******");
        //4.创建邮件
        Message msg=createSimpleMail(session,content);
        //5.发送电子邮件
        ts.sendMessage(msg, msg.getAllRecipients());
    }



    private MimeMessage createSimpleMail(Session session,String content) throws MessagingException {
        //创建邮件对象
        MimeMessage mm = new MimeMessage(session);
        //设置发件人
        mm.setFrom(new InternetAddress("scuwuyu@163.com"));
        //设置收件人
        mm.setRecipient(Message.RecipientType.TO, new InternetAddress("594549507@qq.com"));
        //设置抄送人
//        mm.setRecipient(Message.RecipientType.CC, new InternetAddress(""));

        mm.setSubject("这是一封广告邮件！");
        mm.setContent(StringUtils.isNotEmpty(content)?content:"什么也没有", "text/html;charset=utf-8");
        return mm;
    }
}
