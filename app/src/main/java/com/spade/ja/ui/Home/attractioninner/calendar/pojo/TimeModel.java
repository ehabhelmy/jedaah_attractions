package com.spade.ja.ui.Home.attractioninner.calendar.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.spade.ja.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 3/14/18.
 */

public class TimeModel extends BaseModel implements Parcelable{

    private int id;
    private String date;
    private String startTime;
    private String endTime;

    public TimeModel(int id,String date, String startTime, String endTime) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    protected TimeModel(Parcel in) {
        id = in.readInt();
        date = in.readString();
        startTime = in.readString();
        endTime = in.readString();
    }

    public static final Creator<TimeModel> CREATOR = new Creator<TimeModel>() {
        @Override
        public TimeModel createFromParcel(Parcel in) {
            return new TimeModel(in);
        }

        @Override
        public TimeModel[] newArray(int size) {
            return new TimeModel[size];
        }
    };

    @Override
    public int hashCode() {
        return id;
    }

    public TimeModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(date);
        parcel.writeString(startTime);
        parcel.writeString(endTime);
    }
}
