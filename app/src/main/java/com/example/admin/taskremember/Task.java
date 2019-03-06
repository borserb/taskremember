package com.example.admin.taskremember;


import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private String name;
    private Integer inn;
    private int priprity;

    public Task(String name, int priprity) {
        this.name = name;
        this.priprity = priprity;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInn() {
        return inn;
    }

    public void setInn(Integer inn) {
        this.inn = inn;
    }

    public int getPriprity() {
        return priprity;
    }

    public void setPriprity(int priprity) {
        this.priprity = priprity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.inn);
        dest.writeInt(this.priprity);
    }

    protected Task(Parcel in) {
        this.name = in.readString();
        this.inn = (Integer) in.readValue(Integer.class.getClassLoader());
        this.priprity = in.readInt();
    }

    public static final Creator <Task> CREATOR = new Creator <Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
