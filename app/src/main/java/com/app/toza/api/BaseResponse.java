package com.app.toza.api;

/**
 * This class is used as a Response model. <br/>
 * This class is used as to parse every response of web services of Toza App
 *
 * @param <T> generic type
 */
public class BaseResponse<T> {
    String Status;
    String Message;
    T ResponseData;

    public T getResponseData() {
        return ResponseData;
    }

    public void setResponseData(T responseData) {
        ResponseData = responseData;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
