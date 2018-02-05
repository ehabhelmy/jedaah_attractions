package com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.spade.ja.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/27/17.
 */

public class OrderEventDay extends BaseModel implements Parcelable {

    private String month;
    private String day;
    private String time;

    public OrderEventDay() {
    }

    protected OrderEventDay(Parcel in) {
        month = in.readString();
        day = in.readString();
        time = in.readString();
    }

    public static final Creator<OrderEventDay> CREATOR = new Creator<OrderEventDay>() {
        @Override
        public OrderEventDay createFromParcel(Parcel in) {
            return new OrderEventDay(in);
        }

        @Override
        public OrderEventDay[] newArray(int size) {
            return new OrderEventDay[size];
        }
    };

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(month);
        dest.writeString(day);
        dest.writeString(time);
    }
}
