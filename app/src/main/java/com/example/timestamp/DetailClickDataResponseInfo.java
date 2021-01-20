package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailClickDataResponseInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("user")
    @Expose
    private List<DetailClickDataInfo> detailClickDataInfoList;

    public DetailClickDataResponseInfo(Boolean error, List<DetailClickDataInfo> detailClickDataInfoList) {
        this.error = error;
        this.detailClickDataInfoList = detailClickDataInfoList;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<DetailClickDataInfo> getDetailClickDataInfoList() {
        return detailClickDataInfoList;
    }

    public void setDetailClickDataInfoList(List<DetailClickDataInfo> detailClickDataInfoList) {
        this.detailClickDataInfoList = detailClickDataInfoList;
    }
}
