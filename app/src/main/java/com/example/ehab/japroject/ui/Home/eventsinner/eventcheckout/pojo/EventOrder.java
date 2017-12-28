package com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;

import java.util.ArrayList;

/**
 * Created by ehab on 12/27/17.
 */

public class EventOrder extends BaseModel implements Parcelable{

    private String eventTitle;
    private String email;
    private String name;
    private String mobile;
    private String national;
    private String paymentMethod;
    private ArrayList<OrderEventDay> eventDateDays;
    private String vipPrice;
    private String regularPrice;
    private String tickets;

    public EventOrder() {
    }

    protected EventOrder(Parcel in) {
        eventTitle = in.readString();
        email = in.readString();
        name = in.readString();
        mobile = in.readString();
        national = in.readString();
        paymentMethod = in.readString();
        eventDateDays = in.createTypedArrayList(OrderEventDay.CREATOR);
        vipPrice = in.readString();
        regularPrice = in.readString();
        tickets = in.readString();
    }

    public static final Creator<EventOrder> CREATOR = new Creator<EventOrder>() {
        @Override
        public EventOrder createFromParcel(Parcel in) {
            return new EventOrder(in);
        }

        @Override
        public EventOrder[] newArray(int size) {
            return new EventOrder[size];
        }
    };

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
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

    public ArrayList<OrderEventDay> getEventDateDays() {
        return eventDateDays;
    }

    public void setEventDateDays(ArrayList<OrderEventDay> eventDateDays) {
        this.eventDateDays = eventDateDays;
    }

    public String getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(String vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventTitle);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(mobile);
        dest.writeString(national);
        dest.writeString(paymentMethod);
        dest.writeTypedList(eventDateDays);
        dest.writeString(vipPrice);
        dest.writeString(regularPrice);
        dest.writeString(tickets);
    }
}
