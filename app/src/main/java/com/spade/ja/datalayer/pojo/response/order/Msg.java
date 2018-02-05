
package com.spade.ja.datalayer.pojo.response.order;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Msg extends BaseModel{

    @SerializedName("errors")
    @Expose
    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
