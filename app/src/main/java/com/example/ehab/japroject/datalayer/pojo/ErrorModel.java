package com.example.ehab.japroject.datalayer.pojo;

/**
 * Created by ehab on 12/10/17.
 */

public class ErrorModel extends BaseModel {

    private int code;
    private String message;
    private String title;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
