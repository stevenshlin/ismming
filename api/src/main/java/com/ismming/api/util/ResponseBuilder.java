package com.ismming.api.util;

import com.ismming.api.exception.ErrorMessage;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public final class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static Response ok(Object entity) {
        return Response.ok(entity).build();
    }

    public static Response ok(String name, Object entity) {
        Map<String, Object> singleMap = ImmutableMap.of(name, entity);
        return Response.ok(singleMap).build();
    }

    public static Response error(ErrorMessage errorMessage) {
        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
