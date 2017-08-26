package br.com.herberton.tcc.puc.poc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ImportResource("/WEB-INF/applicationContext.xml")
public class AppConfig extends WebMvcConfigurerAdapter {

}