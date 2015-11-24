package com.ismming.api.exception;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public final class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 7619219919862206978L;

    private int status;

    private String message;

    private ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorMessage newInstance(int status, String message) {
        return new ErrorMessage(status, message);
    }

    public static ErrorMessage newInstance(Response.Status status) {
        return new ErrorMessage(status.getStatusCode(), status.getReasonPhrase());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
