package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageDetailResponseInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("images")
    @Expose
    private List<ImageDetailInfo> imageDetailInfoList;

    public ImageDetailResponseInfo(Boolean error, List<ImageDetailInfo> imageDetailInfoList) {
        this.error = error;
        this.imageDetailInfoList = imageDetailInfoList;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<ImageDetailInfo> getImageDetailInfoList() {
        return imageDetailInfoList;
    }

    public void setImageDetailInfoList(List<ImageDetailInfo> imageDetailInfoList) {
        this.imageDetailInfoList = imageDetailInfoList;
    }
}
