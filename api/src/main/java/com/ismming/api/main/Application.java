package com.ismming.api.main;

import com.ismming.api.config.JerseyConfig;
import com.ismming.api.config.JettyConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.ismming.api"})
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean jerseyServlet() {
        final ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/api/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
        return registration;
    }

    @Bean
    public EmbeddedServletContainerFactory containerFactory(
            @Value("${server.port:8080}") final String port,
            @Value("${jetty.threadPool.maxThreads:200}") final String maxThreads,
            @Value("${jetty.threadPool.minThreads:8}") final String minThreads,
            @Value("${jetty.threadPool.idleTimeout:60000}") final String idleTimeout) {
        final JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory(Integer.valueOf(port));
        factory.addServerCustomizers(new JettyConfig(maxThreads, minThreads, idleTimeout));
        return factory;
    }
}