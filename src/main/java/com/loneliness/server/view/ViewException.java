package com.loneliness.server.view;

public class ViewException {
    private StringBuilder exceptionMessage=new StringBuilder();
    private StringBuilder exception=new StringBuilder();

    public ViewException(String exceptionMessage, String exception){
        this.exceptionMessage.append(exceptionMessage);
        this.exception.append(exception);
    }

    public ViewException(StringBuilder exception) {
        this.exception = exception;
    }

    public StringBuilder getExceptionMessage() {
        return exceptionMessage;
    }

    public StringBuilder getException() {
        return exception;
    }
}
