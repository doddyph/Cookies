package me.nitelab.cookies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dph on 06/08/17.
 */

public class Country {
    private String name;
    @SerializedName("alpha2_code")
    private String alphaCode2;
    @SerializedName("alpha3_code")
    private String alphaCode3;

    public String getName() {
        return name;
    }

    public String getAlphaCode2() {
        return alphaCode2;
    }

    public String getAlphaCode3() {
        return alphaCode3;
    }
}
