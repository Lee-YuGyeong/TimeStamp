package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuResponseInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("menu")
    @Expose
    private List<MenuInfo> menuInfoList;

    public MenuResponseInfo(Boolean error, List<MenuInfo> menuInfoList) {
        this.error = error;
        this.menuInfoList = menuInfoList;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<MenuInfo> getMenuInfoList() {
        return menuInfoList;
    }

    public void setMenuInfoList(List<MenuInfo> menuInfoList) {
        this.menuInfoList = menuInfoList;
    }
}
