package com.spade.ja.ui.Home.attractioninner.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionExceptionalDate;
import com.spade.ja.datalayer.pojo.response.attractioninner.Day;

import java.util.List;

/**
 * Created by ehab on 3/14/18.
 */

public class AttractionPaymentData implements Parcelable {

    private List<Day> weekDays;
    private List<AttractionExceptionalDate> exceptionalDates;
    private boolean isCredit;
    private boolean isCash;
    private boolean isPayLater;
    private int maxPayLaterTickets;
    private int maxCashTickets;
    private int attractionId;
    private String attractionTitle;

    public AttractionPaymentData(List<Day> weekDays, List<AttractionExceptionalDate> exceptionalDates, boolean isCredit, boolean isCash, boolean isPayLater, int maxPayLaterTickets, int maxCashTickets, int attractionId, String attractionTitle) {
        this.weekDays = weekDays;
        this.exceptionalDates = exceptionalDates;
        this.isCredit = isCredit;
        this.isCash = isCash;
        this.isPayLater = isPayLater;
        this.maxPayLaterTickets = maxPayLaterTickets;
        this.maxCashTickets = maxCashTickets;
        this.attractionId = attractionId;
        this.attractionTitle = attractionTitle;
    }


    protected AttractionPaymentData(Parcel in) {
        isCredit = in.readByte() != 0;
        isCash = in.readByte() != 0;
        isPayLater = in.readByte() != 0;
        maxPayLaterTickets = in.readInt();
        maxCashTickets = in.readInt();
        attractionId = in.readInt();
        attractionTitle = in.readString();
    }

    public static final Creator<AttractionPaymentData> CREATOR = new Creator<AttractionPaymentData>() {
        @Override
        public AttractionPaymentData createFromParcel(Parcel in) {
            return new AttractionPaymentData(in);
        }

        @Override
        public AttractionPaymentData[] newArray(int size) {
            return new AttractionPaymentData[size];
        }
    };

    public List<Day> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<Day> weekDays) {
        this.weekDays = weekDays;
    }

    public List<AttractionExceptionalDate> getExceptionalDates() {
        return exceptionalDates;
    }

    public void setExceptionalDates(List<AttractionExceptionalDate> exceptionalDates) {
        this.exceptionalDates = exceptionalDates;
    }

    public boolean isCredit() {
        return isCredit;
    }

    public void setCredit(boolean credit) {
        isCredit = credit;
    }

    public boolean isCash() {
        return isCash;
    }

    public void setCash(boolean cash) {
        isCash = cash;
    }

    public boolean isPayLater() {
        return isPayLater;
    }

    public void setPayLater(boolean payLater) {
        isPayLater = payLater;
    }

    public int getMaxPayLaterTickets() {
        return maxPayLaterTickets;
    }

    public void setMaxPayLaterTickets(int maxPayLaterTickets) {
        this.maxPayLaterTickets = maxPayLaterTickets;
    }

    public int getMaxCashTickets() {
        return maxCashTickets;
    }

    public void setMaxCashTickets(int maxCashTickets) {
        this.maxCashTickets = maxCashTickets;
    }

    public int getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
    }

    public String getAttractionTitle() {
        return attractionTitle;
    }

    public void setAttractionTitle(String attractionTitle) {
        this.attractionTitle = attractionTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (isCredit ? 1 : 0));
        parcel.writeByte((byte) (isCash ? 1 : 0));
        parcel.writeByte((byte) (isPayLater ? 1 : 0));
        parcel.writeInt(maxPayLaterTickets);
        parcel.writeInt(maxCashTickets);
        parcel.writeInt(attractionId);
        parcel.writeString(attractionTitle);
    }
}
