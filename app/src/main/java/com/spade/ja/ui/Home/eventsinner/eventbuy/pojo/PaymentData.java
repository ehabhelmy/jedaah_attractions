package com.spade.ja.ui.Home.eventsinner.eventbuy.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.datalayer.pojo.response.eventinner.EventTicket;
import com.spade.ja.datalayer.pojo.response.eventinner.TicketDate;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.OrderEventDay;

import java.util.ArrayList;

/**
 * Created by ehab on 12/27/17.
 */

public class PaymentData extends BaseModel implements Parcelable {

    private boolean creditCard;
    private boolean cash;
    private boolean payLater;
    private int payLaterTickets;
    private int cashTickets;
    private boolean nationalRequired;
    private ArrayList<OrderEventDay> eventDateDays;
    private String vipPrice;
    private String regularPrice;
    private String eventTitle;
    private int eventId;
    private ArrayList<EventTicket> tickets;
    private ArrayList<TicketDate> ticketDates;

    public PaymentData() {
    }

    protected PaymentData(Parcel in) {
        creditCard = in.readByte() != 0;
        cash = in.readByte() != 0;
        payLater = in.readByte() != 0;
        payLaterTickets = in.readInt();
        cashTickets = in.readInt();
        nationalRequired = in.readByte() != 0;
        eventDateDays = in.createTypedArrayList(OrderEventDay.CREATOR);
        vipPrice = in.readString();
        regularPrice = in.readString();
        eventTitle = in.readString();
        eventId = in.readInt();
        tickets = in.createTypedArrayList(EventTicket.CREATOR);
        ticketDates = in.createTypedArrayList(TicketDate.CREATOR);
    }

    public static final Creator<PaymentData> CREATOR = new Creator<PaymentData>() {
        @Override
        public PaymentData createFromParcel(Parcel in) {
            return new PaymentData(in);
        }

        @Override
        public PaymentData[] newArray(int size) {
            return new PaymentData[size];
        }
    };

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public ArrayList<EventTicket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<EventTicket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<TicketDate> getTicketDates() {
        return ticketDates;
    }

    public void setTicketDates(ArrayList<TicketDate> ticketDates) {
        this.ticketDates = ticketDates;
    }

    public ArrayList<OrderEventDay> getEventDateDays() {
        return eventDateDays;
    }

    public void setEventDateDays(ArrayList<OrderEventDay> eventDateDays) {
        this.eventDateDays = eventDateDays;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public boolean isNationalRequired() {
        return nationalRequired;
    }

    public void setNationalRequired(boolean nationalRequired) {
        this.nationalRequired = nationalRequired;
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

    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (creditCard ? 1 : 0));
        dest.writeByte((byte) (cash ? 1 : 0));
        dest.writeByte((byte) (payLater ? 1 : 0));
        dest.writeInt(payLaterTickets);
        dest.writeInt(cashTickets);
        dest.writeByte((byte) (nationalRequired ? 1 : 0));
        dest.writeTypedList(eventDateDays);
        dest.writeString(vipPrice);
        dest.writeString(regularPrice);
        dest.writeString(eventTitle);
        dest.writeInt(eventId);
        dest.writeTypedList(tickets);
        dest.writeTypedList(ticketDates);
    }
}
