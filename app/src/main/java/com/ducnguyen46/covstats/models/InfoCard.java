package com.ducnguyen46.covstats.models;

import com.ducnguyen46.covstats.R;

import static com.ducnguyen46.covstats.constant.Constant.*;

public class InfoCard {
    private String infoType;
    private int imgInfoType;
    private int statsInfo;

    public InfoCard(String infoType, int statsInfo) {
        this.infoType = infoType;
        this.statsInfo = statsInfo;
        this.imgInfoType = getImg();
    }

    public InfoCard() {
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public int getImgInfoType() {
        return imgInfoType;
    }

    public int getStatsInfo() {
        return statsInfo;
    }

    public void setStatsInfo(int statsInfo) {
        this.statsInfo = statsInfo;
    }

    private int getImg(){
        switch (infoType){
            case NEW_CASE:
            case TOTAL_CASE:
                return R.drawable.img_coronavirus;

            case NEW_RECOVERED:
            case TOTAL_RECOVERED:
                return R.drawable.img_recovered;

            case NEW_DEATH:
            case TOTAL_DEATH:
                return R.drawable.img_dead;

            default:
                return 0;
        }
    }
}
