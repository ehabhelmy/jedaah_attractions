package com.spade.ja.ui.authentication.foretpassword.code;

import com.spade.ja.datalayer.pojo.response.code.ResetCodeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.code.Code;

import javax.inject.Inject;

public class GetCodePresenter extends BasePresenter<GetCodeContract.View> implements GetCodeContract.Presenter {

    private Code code;

    private BaseCallback<ResetCodeResponse> resetCodeResponseBaseCallback = new BaseCallback<ResetCodeResponse>() {
        @Override
        public void onSuccess(ResetCodeResponse model) {
            if (isViewAlive.get()){
                getView().hideLoading();
                jaNavigationManager.goToResetPassword(model.getData().getForgetCode());
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().hideLoading();
                getView().showError(message);
            }
        }
    };

    @Inject
    public GetCodePresenter(Code code) {
        this.code = code;
    }

    @Override
    public void getCode(String email) {
        getView().showLoading();
        code.getCode(email,resetCodeResponseBaseCallback);
    }

    @Override
    public void unSubscribe() {
        code.unSubscribe();
    }
}
