package com.mooracle.stormy.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Hour implements Parcelable {//: implement parcelable
    private long time;
    private String summary;
    private double temperature;
    private String icon;
    private String timeZone;

    // constructors:
    public Hour() {
    }

    public Hour(long time, String summary, double temperature, String icon, String timeZone) {
        this.time = time;
        this.summary = summary;
        this.temperature = temperature;
        this.icon = icon;
        this.timeZone = timeZone;
    }

    protected Hour(Parcel in) {
        time = in.readLong();
        summary = in.readString();
        temperature = in.readDouble();
        icon = in.readString();
        timeZone = in.readString();
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    //getters and setters:
    public String getTime() {
        // instantiate new date time formatter called formatter with format hour and AM/PM (ex: 12 AM)
        SimpleDateFormat formatter = new SimpleDateFormat("h a");

        //set the timezone to avoid auto utilization of GMT
        TimeZone zone = TimeZone.getTimeZone(timeZone);
        formatter.setTimeZone(zone);

        //convert time into milliseconds:
        Date dateTime = new Date(time*1000);

        //return the formatted time:
        return formatter.format(dateTime);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTemperature() {
        return (int)Math.round(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getIcon() {
        //return int from the Forecast.getIconId method by passing the String icon parameter:
        return Forecast.getIconId(icon);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(time);
        dest.writeString(summary);
        dest.writeDouble(temperature);
        dest.writeString(icon);
        dest.writeString(timeZone);
    }
}
