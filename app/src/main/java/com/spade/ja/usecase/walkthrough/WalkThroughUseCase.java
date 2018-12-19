package com.spade.ja.usecase.walkthrough;

import com.spade.ja.datalayer.DataRepository;

import javax.inject.Inject;

public class WalkThroughUseCase {

    private DataRepository dataRepository;

    @Inject
    public WalkThroughUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public boolean isFirstInstall(){
        return dataRepository.isFirstInstall();
    }

    public void setWalkTroughAppeared(){
        dataRepository.walkThroughAppeared();
    }
}
