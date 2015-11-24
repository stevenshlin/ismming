package com.ismming.api.resource;

import com.ismming.api.exception.GenericExceptionMapper;
import com.ismming.api.filter.NoCacheControlFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class Application extends ResourceConfig {

    public Application() {
        register(RequestContextFilter.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(JacksonFeature.class);
        register(GenericExceptionMapper.class);
        register(NoCacheControlFilter.class);
        register(CategoryResource.class);
    }
}
