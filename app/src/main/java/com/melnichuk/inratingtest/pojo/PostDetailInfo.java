package com.melnichuk.inratingtest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostDetailInfo {
    @SerializedName("data")
    @Expose
    private List<UserInfo> data;

    public List<UserInfo> getData() {
        return data;
    }

    public void setData(List<UserInfo> data) {
        this.data = data;
    }
}