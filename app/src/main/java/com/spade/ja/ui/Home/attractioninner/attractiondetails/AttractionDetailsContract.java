package com.spade.ja.ui.Home.attractioninner.attractiondetails;

import com.spade.ja.datalayer.pojo.response.attractioninner.Data;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by ehab on 3/9/18.
 */

public interface AttractionDetailsContract {
    interface View extends BaseView,ErrorView,ProgressView {
        void setupAttractionDetails(Data data);
    }

    interface Presenter extends Unsubscribable {
        void openNavigationView(double lat,double lng);
        void like();
        void openPaymentView();
        void showFullScreenPhoto(String imageUrl,String title);
    }
}
