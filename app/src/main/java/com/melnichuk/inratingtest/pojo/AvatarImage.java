package com.melnichuk.inratingtest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvatarImage {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("url_medium")
    @Expose
    private String urlMedium;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlMedium() {
        return urlMedium;
    }

    public void setUrlMedium(String urlMedium) {
        this.urlMedium = urlMedium;
    }
}