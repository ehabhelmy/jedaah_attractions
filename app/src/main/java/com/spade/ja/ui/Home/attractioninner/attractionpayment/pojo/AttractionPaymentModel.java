package com.spade.ja.ui.Home.attractioninner.attractionpayment.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.ui.Home.attractioninner.calendar.pojo.TimeModel;

/**
 * Created by ehab on 3/14/18.
 */

public class AttractionPaymentModel extends BaseModel implements Parcelable {

    private boolean creditCard;
    private boolean cash;
    private boolean payLater;
    private int payLaterTickets;
    private int cashTickets;
    private boolean nationalRequired;
    private TimeModel time;
    private String attractionTitle;
    private int attractionId;

    public AttractionPaymentModel(boolean creditCard, boolean cash, boolean payLater, int payLaterTickets, int cashTickets, boolean nationalRequired, TimeModel time, String attractionTitle, int attractionId) {
        this.creditCard = creditCard;
        this.cash = cash;
        this.payLater = payLater;
        this.payLaterTickets = payLaterTickets;
        this.cashTickets = cashTickets;
        this.nationalRequired = nationalRequired;
        this.time = time;
        this.attractionTitle = attractionTitle;
        this.attractionId = attractionId;
    }

    protected AttractionPaymentModel(Parcel in) {
        creditCard = in.readByte() != 0;
        cash = in.readByte() != 0;
        payLater = in.readByte() != 0;
        payLaterTickets = in.readInt();
        cashTickets = in.readInt();
        nationalRequired = in.readByte() != 0;
        time = in.readParcelable(TimeModel.class.getClassLoader());
        attractionTitle = in.readString();
        attractionId = in.readInt();
    }

    public static final Creator<AttractionPaymentModel> CREATOR = new Creator<AttractionPaymentModel>() {
        @Override
        public AttractionPaymentModel createFromParcel(Parcel in) {
            return new AttractionPaymentModel(in);
        }

        @Override
        public AttractionPaymentModel[] newArray(int size) {
            return new AttractionPaymentModel[size];
        }
    };

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public boolean isPayLater() {
        return payLater;
    }

    public void setPayLater(boolean payLater) {
        this.payLater = payLater;
    }

    public int getPayLaterTickets() {
        return payLaterTickets;
    }

    public void setPayLaterTickets(int payLaterTickets) {
        this.payLaterTickets = payLaterTickets;
    }

    public int getCashTickets() {
        return cashTickets;
    }

    public void setCashTickets(int cashTickets) {
        this.cashTickets = cashTickets;
    }

    public boolean isNationalRequired() {
        return nationalRequired;
    }

    public void setNationalRequired(boolean nationalRequired) {
        this.nationalRequired = nationalRequired;
    }

    public TimeModel getTime() {
        return time;
    }

    public void setTime(TimeModel time) {
        this.time = time;
    }

    public String getAttractionTitle() {
        return attractionTitle;
    }

    public void setAttractionTitle(String attractionTitle) {
        this.attractionTitle = attractionTitle;
    }
    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

    public int getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (creditCard ? 1 : 0));
        parcel.writeByte((byte) (cash ? 1 : 0));
        parcel.writeByte((byte) (payLater ? 1 : 0));
        parcel.writeInt(payLaterTickets);
        parcel.writeInt(cashTickets);
        parcel.writeByte((byte) (nationalRequired ? 1 : 0));
        parcel.writeParcelable(time, i);
        parcel.writeString(attractionTitle);
        parcel.writeInt(attractionId);
    }

}
