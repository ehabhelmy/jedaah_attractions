
package com.spade.ja.datalayer.pojo.response.code;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;

public class Data extends BaseModel {

    @SerializedName("forget code")
    @Expose
    private String forgetCode;

    public String getForgetCode() {
        return forgetCode;
    }

    public void setForgetCode(String forgetCode) {
        this.forgetCode = forgetCode;
    }

}
