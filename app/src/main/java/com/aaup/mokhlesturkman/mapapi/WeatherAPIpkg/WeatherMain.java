package com.aaup.mokhlesturkman.mapapi.WeatherAPIpkg;

import com.google.gson.annotations.SerializedName;

public class WeatherMain {
    @SerializedName("temp")
    private Double mTemp;
    @SerializedName("temp_max")
    private Double mTempMax;
    @SerializedName("temp_min")
    private Double mTempMin;

    public Double getmTemp() {
        return mTemp;
    }

    public void setmTemp(Double mTemp) {
        this.mTemp = mTemp;
    }

    public Double getmTempMax() {
        return mTempMax;
    }

    public void setmTempMax(Double mTempMax) {
        this.mTempMax = mTempMax;
    }

    public Double getmTempMin() {
        return mTempMin;
    }

    public void setmTempMin(Double mTempMin) {
        this.mTempMin = mTempMin;
    }
}
