package com.example.ehab.japroject.datalayer.remote;

/**
 * Created by ehab on 12/2/17.
 */

public class ServiceResponse {

    private ServiceError serviceError;
    private Object data;
    private int code;

    public ServiceResponse(ServiceError serviceError) {
        this.serviceError = serviceError;
    }

    public ServiceResponse(int code, Object data) {
        this.data = data;
        this.code = code;
    }

    public ServiceResponse(ServiceError serviceError, Object data) {
        this.serviceError = serviceError;
        this.data = data;
    }

    public ServiceResponse(Object data) {
        this.data = data;
    }

    public ServiceError getServiceError() {
        return serviceError;
    }

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

}
