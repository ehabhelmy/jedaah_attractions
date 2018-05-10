
package com.spade.ja.datalayer.pojo.response.about;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;

public class Datum extends BaseModel{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("paragraph")
    @Expose
    private String paragraph;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

}
