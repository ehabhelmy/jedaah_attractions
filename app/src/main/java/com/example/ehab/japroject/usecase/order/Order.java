package com.example.ehab.japroject.usecase.order;

import com.example.ehab.japroject.usecase.Unsubscribable;

import javax.inject.Inject;

/**
 * Created by ehab on 12/25/17.
 */

public class Order implements Unsubscribable {

    @Inject
    public Order() {
    }

    @Override
    public void unSubscribe() {

    }
}
