package com.spade.ja.ui.Home.attractioninner.adapter;

import com.spade.ja.datalayer.pojo.response.attractioninner.Data;
import com.spade.ja.ui.Home.attractioninner.pojo.AttractionPaymentData;

/**
 * Created by ehab on 3/14/18.
 */

public class AttractionPaymentAdapter {

    public static AttractionPaymentData convertToPaymentData (Data data) {

        return new AttractionPaymentData(data.getDays(),data.getAttractionExceptionalDates(), data.getCreditCard() == 1, data.getCashOnDelivery() == 1, data.getPayLater() == 1,data.getMaxOfPayLaterTickets() != null ? data.getMaxOfPayLaterTickets():0,data.getMaxOfCashTickets() != null ? data.getMaxOfCashTickets() : 0,data.getId(),data.getTitle());
    }
}
