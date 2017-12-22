package com.example.ehab.japroject.ui.Base.listener;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/1/17.
 */

public interface BaseCallback<M extends BaseModel> {

    void onSuccess(M model);

    void onError(String message);
}
