package com.aaup.mokhlesturkman.mapapi.WeatherAPIpkg;
import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("main")
    String mMain;
    @SerializedName("description")
    String mDescription;

    public String getmMain() {
        return mMain;
    }

    public void setmMain(String mMain) {
        this.mMain = mMain;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }




}
