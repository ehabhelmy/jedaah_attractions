package com.spade.ja.datalayer.pojo.response.category;

/**
 * Created by Romisaa.Attia on 12/13/2017.
 */

public interface CategoryCallback<M extends Object> {

    void onSuccess(M model);

    void onError();
}
