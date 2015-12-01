package com.ismming.api.filter;

import com.google.common.base.Strings;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.hk2.api.ServiceLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.inject.Inject;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSResponseFilter implements ContainerResponseFilter {

    public static final String ORIGIN = "Origin";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";

    public static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";

    public static final String ALLOW_METHODS = "GET, POST, DELETE, PUT";

    private String[] allowOriginList;

    @Inject
    public CORSResponseFilter(ServiceLocator locator) {
        if (locator == null) {
            return;
        }
        ApplicationContext ctx = locator.getService(ApplicationContext.class);
        if (ctx != null) {
            AbstractApplicationContext webAppContext = (AbstractApplicationContext) ctx;
            String allowOriginListStr = webAppContext.getBeanFactory().resolveEmbeddedValue("${cors.accessControlAllowOriginList}");
            if (!Strings.isNullOrEmpty(allowOriginListStr)) {
                allowOriginList = StringUtils.split(allowOriginListStr, ",");
            } else {
                allowOriginList = new String[0];
            }
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        String origin = requestContext.getHeaderString(ORIGIN);
        if (!Strings.isNullOrEmpty(origin) && validateOrigin(origin)) {
            addCORSHeader(responseContext, ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            if (needAddPreflightHeaders(requestContext)) {
                addCORSHeader(responseContext, ACCESS_CONTROL_ALLOW_METHODS, ALLOW_METHODS);
                String requestHeaders = requestContext.getHeaderString(ACCESS_CONTROL_REQUEST_HEADERS);
                if (!Strings.isNullOrEmpty(requestHeaders)) {
                    addCORSHeader(responseContext, ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders);
                }
                addCORSHeader(responseContext, ACCESS_CONTROL_MAX_AGE, "1800");
            }
        }
    }

    private boolean validateOrigin(String origin) {
        return isWildcardOrigin() || ArrayUtils.contains(allowOriginList, origin);
    }

    private boolean isWildcardOrigin() {
        return allowOriginList.length == 1 && StringUtils.equals(allowOriginList[0], "*");
    }

    private void addCORSHeader(ContainerResponseContext responseContext, String headerName, String origin) {
        if (Strings.isNullOrEmpty(responseContext.getHeaderString(headerName))) {
            responseContext.getHeaders().add(headerName, origin);
        }
    }

    private boolean needAddPreflightHeaders(ContainerRequestContext requestContext) {
        return StringUtils.equals(requestContext.getMethod(), HttpMethod.OPTIONS);
    }
}
