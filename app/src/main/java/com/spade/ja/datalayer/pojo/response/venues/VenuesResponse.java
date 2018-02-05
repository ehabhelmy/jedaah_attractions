
package com.spade.ja.datalayer.pojo.response.venues;

import java.util.List;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenuesResponse extends BaseModel{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Venue> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Venue> getData() {
        return data;
    }

    public void setData(List<Venue> data) {
        this.data = data;
    }

}
