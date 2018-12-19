package com.spade.ja.ui.walkthrough;

import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.usecase.walkthrough.WalkThroughUseCase;

import javax.inject.Inject;

public class WalkThroughPresenter extends BasePresenter<WalkThroughContract.View> implements WalkThroughContract.Presenter {

    @Inject
    public WalkThroughPresenter(WalkThroughUseCase walkThroughUseCase) {
        walkThroughUseCase.setWalkTroughAppeared();
    }

    @Override
    public void skip() {
        jaNavigationManager.goToAuthActivity(null);
    }
}
