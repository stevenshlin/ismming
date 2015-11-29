package com.ismming.api.exception;

import com.google.common.base.Strings;
import com.ismming.api.util.ResponseBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Enumeration;
import java.util.UUID;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = LogManager.getLogger(GenericExceptionMapper.class.getName());
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof IllegalArgumentException) {
            ErrorMessage errorMessage = ErrorMessage.newInstance(Status.BAD_REQUEST.getStatusCode(), exception.getMessage());
            return ResponseBuilder.error(errorMessage);
        }

        if (exception instanceof NotFoundException) {
            ErrorMessage errorMessage = ErrorMessage.newInstance(Status.NOT_FOUND);
            return ResponseBuilder.error(errorMessage);
        }

        if (exception instanceof NotAllowedException) {
            ErrorMessage errorMessage = ErrorMessage.newInstance(Status.METHOD_NOT_ALLOWED);
            return ResponseBuilder.error(errorMessage);
        }

        if (exception instanceof NotSupportedException) {
            ErrorMessage errorMessage = ErrorMessage.newInstance(Status.UNSUPPORTED_MEDIA_TYPE);
            return ResponseBuilder.error(errorMessage);
        }

        if (exception instanceof NotAcceptableException) {
            ErrorMessage errorMessage = ErrorMessage.newInstance(Status.NOT_ACCEPTABLE);
            return ResponseBuilder.error(errorMessage);
        }

        if (exception instanceof ForbiddenException) {
            ErrorMessage errorMessage = ErrorMessage.newInstance(Status.FORBIDDEN);
            return ResponseBuilder.error(errorMessage);
        }
        ExceptionMessage exceptionMessage = createExceptionMessage(exception);
        logException(exception, exceptionMessage);
        ErrorMessage errorMessage = ErrorMessage.newInstance(Status.INTERNAL_SERVER_ERROR.getStatusCode(), exceptionMessage.getMessage());
        return ResponseBuilder.error(errorMessage);
    }

    private ExceptionMessage createExceptionMessage(Exception exception) {
        String exceptionId = UUID.randomUUID().toString();
        String message = exception.getMessage();
        if (Strings.isNullOrEmpty(message)) {
            message = exception.getClass().getSimpleName();
        }
        return ExceptionMessage.newInstance(exceptionId, message);
    }

    private void logException(Exception exception, ExceptionMessage exceptionMessage) {
        String message = createExceptionLog(exceptionMessage);
        LOG.error(message, exception);
    }

    private String createExceptionLog(ExceptionMessage exceptionMessage) {
        StringBuilder message = new StringBuilder();
        message.append(String.format("=================== Exception Id: %s START ===================\n", exceptionMessage.getExceptionId()));
        if (request != null) {
            message.append(String.format("Request URI: %s\n", request.getPathInfo()));
            message.append(String.format("Request query String: %s\n", request.getQueryString()));

            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = (String) headerNames.nextElement();
                message.append(String.format("Request header: %s:%s\n", headerName, request.getHeader(headerName)));
            }
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    message.append(String.format("Request cookie: %s:%s\n", cookie.getName(), cookie.getValue()));
                }
            }
            Enumeration parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String parameterName = (String) parameterNames.nextElement();
                message.append(String.format("Request param: %s:%s\n", parameterName, request.getParameter(parameterName)));
            }
        }
        message.append(String.format("An exception occurred, message is: %s\n", exceptionMessage.getMessage()));
        message.append(String.format("=================== Exception Id: %s END ===================\n", exceptionMessage.getExceptionId()));
        return message.toString();
    }
}
