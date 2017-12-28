
package com.example.ehab.japroject.datalayer.pojo.response.order;

import java.util.List;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors extends BaseModel{

    @SerializedName("user_national_id")
    @Expose
    private List<String> userNationalId = null;

    public List<String> getUserNationalId() {
        return userNationalId;
    }

    public void setUserNationalId(List<String> userNationalId) {
        this.userNationalId = userNationalId;
    }

}
