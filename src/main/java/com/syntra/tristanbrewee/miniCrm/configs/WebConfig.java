package com.syntra.tristanbrewee.miniCrm.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(basePackages = "com.syntra.tristanbrewee.miniCrm.controllers")
public class WebConfig {
}
