package com.spade.ja.datalayer.pojo.request.search;

import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;

import java.util.List;

/**
 * Created by ehab on 3/20/18.
 */

public class SearchRequest extends BaseModel {

    @SerializedName("search")
    private String searchKeyword;

    @SerializedName("types")
    List<String> types;

    @SerializedName("category")
    List<Integer> categories;

    public SearchRequest(String searchKeyword, List<String> types, List<Integer> categories) {
        this.searchKeyword = searchKeyword;
        this.types = types;
        this.categories = categories;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }
}
