package com.ismming.api.config;

import com.ismming.api.exception.GenericExceptionMapper;
import com.ismming.api.filter.CORSResponseFilter;
import com.ismming.api.filter.NoCacheControlFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("com.ismming.api.resource");
        register(RequestContextFilter.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(CORSResponseFilter.class);
        register(GenericExceptionMapper.class);
        register(JacksonFeature.class);
        register(GenericExceptionMapper.class);
        register(NoCacheControlFilter.class);
    }
}