
package com.spade.ja.datalayer.pojo.response.eventinner;

import android.os.Parcel;
import android.os.Parcelable;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventTicket extends BaseModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("number_of_tickets")
    @Expose
    private Integer numberOfTickets;

    protected EventTicket(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        type = in.readString();
        description = in.readString();
        price = in.readString();
        if (in.readByte() == 0) {
            numberOfTickets = null;
        } else {
            numberOfTickets = in.readInt();
        }
    }

    public static final Creator<EventTicket> CREATOR = new Creator<EventTicket>() {
        @Override
        public EventTicket createFromParcel(Parcel in) {
            return new EventTicket(in);
        }

        @Override
        public EventTicket[] newArray(int size) {
            return new EventTicket[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(type);
        dest.writeString(description);
        dest.writeString(price);
        if (numberOfTickets == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numberOfTickets);
        }
    }
}
