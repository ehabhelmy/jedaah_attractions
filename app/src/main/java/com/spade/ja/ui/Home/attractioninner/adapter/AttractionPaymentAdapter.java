package com.spade.ja.ui.Home.attractioninner.adapter;

import com.spade.ja.datalayer.pojo.response.attractioninner.Data;
import com.spade.ja.ui.Home.attractioninner.pojo.AttractionPaymentData;

/**
 * Created by ehab on 3/14/18.
 */

public class AttractionPaymentAdapter {

    public static AttractionPaymentData convertToPaymentData (Data data) {

        return new AttractionPaymentData(data.getDays(),data.getAttractionExceptionalDates(),data.getCreditCard() ==1 ? true:false,data.getCashOnDelivery() == 1 ? true:false,data.getPayLater() == 1 ? true:false,data.getMaxOfPayLaterTickets(),data.getMaxOfCashTickets(),data.getId(),data.getTitle());
    }
}
