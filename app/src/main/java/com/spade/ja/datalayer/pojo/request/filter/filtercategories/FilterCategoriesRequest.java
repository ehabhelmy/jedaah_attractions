package com.spade.ja.datalayer.pojo.request.filter.filtercategories;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterCategoriesRequest {

    @SerializedName("category")
    private List<Integer> categoryId;

    public FilterCategoriesRequest(List<Integer> categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<Integer> categoryId) {
        this.categoryId = categoryId;
    }
}
