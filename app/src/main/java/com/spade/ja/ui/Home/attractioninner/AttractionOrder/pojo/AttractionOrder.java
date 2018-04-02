package com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.ui.Home.attractioninner.calendar.pojo.TimeModel;
import com.spade.ja.ui.Home.attractioninner.pojo.AttractionPaymentData;

/**
 * Created by ehab on 3/14/18.
 */

public class AttractionOrder extends BaseModel implements Parcelable {

    private String attractionTitle;
    private String email;
    private String name;
    private String mobile;
    private String national;
    private String paymentMethod;
    private TimeModel timeModel;
    private int attractionId;
    private AttractionPaymentData attractionPaymentData;

    public AttractionOrder(String attractionTitle, String email, String name, String mobile, String national, String paymentMethod,TimeModel timeModel, int attractionId,AttractionPaymentData attractionPaymentData) {
        this.attractionTitle = attractionTitle;
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.national = national;
        this.paymentMethod = paymentMethod;
        this.timeModel = timeModel;
        this.attractionId = attractionId;
        this.attractionPaymentData = attractionPaymentData;
    }

    public AttractionOrder() {
    }

    protected AttractionOrder(Parcel in) {
        attractionTitle = in.readString();
        email = in.readString();
        name = in.readString();
        mobile = in.readString();
        national = in.readString();
        paymentMethod = in.readString();
        timeModel = in.readParcelable(TimeModel.class.getClassLoader());
        attractionId = in.readInt();
        attractionPaymentData = in.readParcelable(AttractionPaymentData.class.getClassLoader());
    }

    public static final Creator<AttractionOrder> CREATOR = new Creator<AttractionOrder>() {
        @Override
        public AttractionOrder createFromParcel(Parcel in) {
            return new AttractionOrder(in);
        }

        @Override
        public AttractionOrder[] newArray(int size) {
            return new AttractionOrder[size];
        }
    };

    public String getAttractionTitle() {
        return attractionTitle;
    }

    public void setAttractionTitle(String attractionTitle) {
        this.attractionTitle = attractionTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TimeModel getTimeModel() {
        return timeModel;
    }

    public void setTimeModel(TimeModel timeModel) {
        this.timeModel = timeModel;
    }

    public int getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
    }

    public AttractionPaymentData getAttractionPaymentData() {
        return attractionPaymentData;
    }

    public void setAttractionPaymentData(AttractionPaymentData attractionPaymentData) {
        this.attractionPaymentData = attractionPaymentData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(attractionTitle);
        parcel.writeString(email);
        parcel.writeString(name);
        parcel.writeString(mobile);
        parcel.writeString(national);
        parcel.writeString(paymentMethod);
        parcel.writeParcelable(timeModel, i);
        parcel.writeInt(attractionId);
        parcel.writeParcelable(attractionPaymentData, i);
    }
}
