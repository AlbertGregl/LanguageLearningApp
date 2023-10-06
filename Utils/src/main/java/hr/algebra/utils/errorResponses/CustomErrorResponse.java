package hr.algebra.utils.errorResponses;

import java.util.Map;

public class CustomErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
    private Map<String, String> errors;
    // constructors

    public CustomErrorResponse() {
    }

    public CustomErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public CustomErrorResponse(int status, String message, long timeStamp, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.errors = errors;
    }

    // getters / setters

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

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}