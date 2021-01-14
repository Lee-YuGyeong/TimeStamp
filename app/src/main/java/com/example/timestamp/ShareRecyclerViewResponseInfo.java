package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShareRecyclerViewResponseInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("menu")
    @Expose
    private List<ShareRecyclerViewInfo> shareRecyclerViewInfoList;

    public ShareRecyclerViewResponseInfo(Boolean error, List<ShareRecyclerViewInfo> shareRecyclerViewInfoList) {
        this.error = error;
        this.shareRecyclerViewInfoList = shareRecyclerViewInfoList;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<ShareRecyclerViewInfo> getShareRecyclerViewInfoList() {
        return shareRecyclerViewInfoList;
    }

    public void setShareRecyclerViewInfoList(List<ShareRecyclerViewInfo> shareRecyclerViewInfoList) {
        this.shareRecyclerViewInfoList = shareRecyclerViewInfoList;
    }
}
