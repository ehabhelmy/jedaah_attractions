
package com.example.ehab.japroject.datalayer.pojo.response.order;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg extends BaseModel{

    @SerializedName("errors")
    @Expose
    private Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

}
