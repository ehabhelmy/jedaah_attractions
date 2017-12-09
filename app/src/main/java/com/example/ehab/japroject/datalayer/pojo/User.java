package com.example.ehab.japroject.datalayer.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ehab on 12/2/17.
 */

public class User extends BaseModel implements Parcelable {
    protected User(Parcel in) {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
