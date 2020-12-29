package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("images")
    @Expose
    private List<MyMenuInfo> myMenuInfoList;

    public ResponseInfo(Boolean error, List<MyMenuInfo> myMenuInfoList) {
        this.error = error;
        this.myMenuInfoList = myMenuInfoList;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<MyMenuInfo> getMyMenuInfoList() {
        return myMenuInfoList;
    }

    public void setMyMenuInfoList(List<MyMenuInfo> myMenuInfoList) {
        this.myMenuInfoList = myMenuInfoList;
    }
}
