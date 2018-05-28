package com.gongsi.mini;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = { "classpath*:spring/spring-*" })
@ComponentScan("com.gongsi")
public class AppContext {
}