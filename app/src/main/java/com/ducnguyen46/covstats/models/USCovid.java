package com.ducnguyen46.covstats.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class USCovid {

    public USCovid(int positive, int negative, String dateChecked, int death) {
        this.positive = positive;
        this.negative = negative;
        this.dateChecked = dateChecked;
        this.death = death;
    }

    public USCovid(JSONObject json){
        this.positive = json.optInt("positive");
        this.negative = json.optInt("negative");
        this.dateChecked = json.optString("dateChecked");
        this.death = json.optInt("death");
    }

    public USCovid() {
    }

    @SerializedName("positive")
    @Expose
    @Nullable
    private int positive;

    @SerializedName("negative")
    @Expose
    @Nullable
    private int negative;

    @SerializedName("dateChecked")
    @Expose
    @Nullable
    private String dateChecked;

    @SerializedName("death")
    @Expose
    @Nullable
    private int death;

    @Nullable
    public int getPositive() {
        return positive;
    }

    public void setPositive(@Nullable int positive) {
        this.positive = positive;
    }

    @Nullable
    public int getNegative() {
        return negative;
    }

    public void setNegative(@Nullable int negative) {
        this.negative = negative;
    }

    @Nullable
    public String getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(@Nullable String dateChecked) {
        this.dateChecked = dateChecked;
    }

    @Nullable
    public int getDeath() {
        return death;
    }

    public void setDeath(@Nullable int death) {
        this.death = death;
    }
}
