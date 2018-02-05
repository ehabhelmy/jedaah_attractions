package com.spade.ja.ui.Home.profile.liked_directory;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class LikedDirectoryContract {

    interface View extends BaseView, ErrorView, ProgressView {

    }

    interface Presenter extends Unsubscribable {

    }
}
