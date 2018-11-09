package com.soumit.firebook.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class SingleInfo implements Parcelable {

    private String uid;
    private String name;
    private String batch;
    private String address;
    private String imageLink;
    private String email;
    private String phoneNumber;

    public SingleInfo() {
    }

    protected SingleInfo(Parcel in) {
        uid = in.readString();
        name = in.readString();
        batch = in.readString();
        address = in.readString();
        imageLink = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<SingleInfo> CREATOR = new Creator<SingleInfo>() {
        @Override
        public SingleInfo createFromParcel(Parcel in) {
            return new SingleInfo(in);
        }

        @Override
        public SingleInfo[] newArray(int size) {
            return new SingleInfo[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SingleInfo(String uid, String name, String batch, String address,
                      String imageLink, String email, String phoneNumber) {
        this.uid = uid;
        this.name = name;
        this.batch = batch;
        this.address = address;
        this.imageLink = imageLink;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("batch", batch);
        result.put("address", address);
        result.put("imageLink", imageLink);
        result.put("email", email);
        result.put("phoneNumber", phoneNumber);

        return result;
    }

    @Override
    public String toString() {
        return "SingleInfo{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", batch='" + batch + '\'' +
                ", address='" + address + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(batch);
        dest.writeString(address);
        dest.writeString(imageLink);
        dest.writeString(email);
        dest.writeString(phoneNumber);
    }
}
