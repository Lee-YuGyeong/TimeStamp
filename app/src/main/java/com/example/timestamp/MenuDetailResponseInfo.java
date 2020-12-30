package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuDetailResponseInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("images")
    @Expose
    private List<MenuDetailInfo> menuDetailInfoList;

    public MenuDetailResponseInfo(Boolean error, List<MenuDetailInfo> menuDetailInfoList) {
        this.error = error;
        this.menuDetailInfoList = menuDetailInfoList;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<MenuDetailInfo> getMenuDetailInfoList() {
        return menuDetailInfoList;
    }

    public void setMenuDetailInfoList(List<MenuDetailInfo> menuDetailInfoList) {
        this.menuDetailInfoList = menuDetailInfoList;
    }
}
