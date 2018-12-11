package com.mooracle.stormy.weather;

import com.mooracle.stormy.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Current {
    //define fields variables
    private String locationLabel;
    private String icon;
    private long time;
    private double temperature;
    private double humidity;
    private double percipChance;
    private String summary;
    private String timeZone;

    //constructors

    public Current() {
    }

    public Current(String locationLabel, String icon, long time, double temperature,
                   double humidity, double percipChance, String summary, String timeZone) {
        this.locationLabel = locationLabel;
        this.icon = icon;
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.percipChance = percipChance;
        this.summary = summary;
        this.timeZone = timeZone;
    }

    // getters and setters
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }


    public String getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIconId(){
        // the switch statements are moved to Forecast class thus we can get it from there
        return Forecast.getIconId(icon);
        //Note that we can directly called Forecast with no instatiation because it is Static method!
    }

    public long getTime() {
        return time;
    }

    public String getFormattedTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date dateTime = new Date(time*1000);
        return formatter.format(dateTime);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPercipChance() {
        return percipChance;
    }

    public void setPercipChance(double percipChance) {
        this.percipChance = percipChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
