
package com.example.ehab.japroject.datalayer.pojo.response.category;

import java.util.List;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category extends BaseModel{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Cats> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Cats> getData() {
        return data;
    }

    public void setData(List<Cats> data) {
        this.data = data;
    }

}
