package com.example.admin.taskremember;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
@Entity
public class Task implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int priprity;

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
        dest.writeInt(this.priprity);
    }

    protected Task(Parcel in) {
        this.name = in.readString();
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
