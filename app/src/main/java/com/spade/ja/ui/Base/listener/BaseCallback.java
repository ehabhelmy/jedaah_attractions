package com.spade.ja.ui.Base.listener;

import com.spade.ja.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/1/17.
 */

public interface BaseCallback<M extends BaseModel> {

    void onSuccess(M model);

    void onError(String message);
}
