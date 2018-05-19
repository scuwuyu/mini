package com.gongsi.mini;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author guoqw
 * @since 2017-04-11 13:20
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.gongsi.mini.dao")
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws UnknownHostException {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        ConfigurableEnvironment env = SpringApplication
                .run(Application.class, args).getEnvironment();
        logger.info("\nLocal:  http://127.0.0.1:{}" +
                    "\nExternal: http://{}:{}",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

}
