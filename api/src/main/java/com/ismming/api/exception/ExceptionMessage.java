package com.ismming.api.exception;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public final class ExceptionMessage implements Serializable {

    private static final long serialVersionUID = 4874464532509400367L;

    private String exceptionId;

    private String message;

    private ExceptionMessage(String exceptionId, String message) {
        this.exceptionId = exceptionId;
        this.message = message;
    }

    public static ExceptionMessage newInstance(String exceptionId, String message) {
        return new ExceptionMessage(exceptionId, message);
    }

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
