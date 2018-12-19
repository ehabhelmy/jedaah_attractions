package com.spade.ja.datalayer.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.spade.ja.datalayer.pojo.response.category.Cats;

import java.util.List;

public class SearchCriteria implements Parcelable {

    private int monthNumber;
    private Double latitiude;
    private Double longitude;
    private boolean isWeeklySuggested;
    private List<Cats> categoriesNames;

    protected SearchCriteria(Parcel in) {
        monthNumber = in.readInt();
        if (in.readByte() == 0) {
            latitiude = null;
        } else {
            latitiude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        isWeeklySuggested = in.readByte() != 0;
        categoriesNames = in.createTypedArrayList(Cats.CREATOR);
    }

    public static final Creator<SearchCriteria> CREATOR = new Creator<SearchCriteria>() {
        @Override
        public SearchCriteria createFromParcel(Parcel in) {
            return new SearchCriteria(in);
        }

        @Override
        public SearchCriteria[] newArray(int size) {
            return new SearchCriteria[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(monthNumber);
        if (latitiude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitiude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        dest.writeByte((byte) (isWeeklySuggested ? 1 : 0));
        dest.writeTypedList(categoriesNames);
    }

    public SearchCriteria(int monthNumber, Double latitiude, Double longitude, boolean isWeeklySuggested, List<Cats> categoriesNames) {
        this.monthNumber = monthNumber;
        this.latitiude = latitiude;
        this.longitude = longitude;
        this.isWeeklySuggested = isWeeklySuggested;
        this.categoriesNames = categoriesNames;
    }

    public SearchCriteria(Double latitiude, Double longitude, boolean isWeeklySuggested, List<Cats> categoriesNames) {
        this.latitiude = latitiude;
        this.longitude = longitude;
        this.isWeeklySuggested = isWeeklySuggested;
        this.categoriesNames = categoriesNames;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public Double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(Double latitiude) {
        this.latitiude = latitiude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public boolean isWeeklySuggested() {
        return isWeeklySuggested;
    }

    public void setWeeklySuggested(boolean weeklySuggested) {
        isWeeklySuggested = weeklySuggested;
    }

    public List<Cats> getCategoriesNames() {
        return categoriesNames;
    }

    public void setCategoriesNames(List<Cats> categoriesNames) {
        this.categoriesNames = categoriesNames;
    }
}
