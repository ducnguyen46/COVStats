package com.ducnguyen46.covstats.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CovidCountry implements Serializable {
    private String country;
    private String countryCode;
    private int newConfirmed;
    private int totalConfirmed;
    private int newDeath;
    private int totalDeath;
    private int newRecovered;
    private int totalRecovered;
    private Date date;

    public CovidCountry() {
    }

    public CovidCountry(String country, String countryCode, int newConfirmed, int totalConfirmed, int newRecovered, int totalRecovered, int newDeaths, int totalDeaths, Date date) {
        this.country = country;
        this.countryCode = countryCode;
        this.newConfirmed = newConfirmed;
        this.totalConfirmed = totalConfirmed;
        this.newDeath = newDeaths;
        this.totalDeath = totalDeaths;
        this.newRecovered = newRecovered;
        this.totalRecovered = totalRecovered;
        this.date = date;
    }

    public CovidCountry(JSONObject json){
        try {
            this.country = json.optString("Country") != null
                            ? json.optString("Country") : "Global";
            this.countryCode = json.optString("CountryCode") != null
                    ? json.optString("CountryCode") : "Global" ;
            this.newConfirmed = json.getInt("NewConfirmed");
            this.totalConfirmed = json.getInt("TotalConfirmed");
            this.newDeath = json.getInt("NewDeaths");
            this.totalDeath = json.getInt("TotalDeaths");
            this.newRecovered = json.getInt("NewRecovered");
            this.totalRecovered = json.getInt("TotalRecovered");
            this.date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(json.getString("Date"));
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getNewDeath() {
        return newDeath;
    }

    public void setNewDeath(int newDeath) {
        this.newDeath = newDeath;
    }

    public int getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(int totalDeath) {
        this.totalDeath = totalDeath;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CovidCountry{" +
                "country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", newConfirmed=" + newConfirmed +
                ", totalConfirmed=" + totalConfirmed +
                ", newDeaths=" + newDeath +
                ", totalDeaths=" + totalDeath +
                ", newRecovered=" + newRecovered +
                ", totalRecovered=" + totalRecovered +
                ", date=" + date +
                '}';
    }
}
