package me.toptas.rssreader.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Feed implements Serializable {

    @SerializedName("i")
    private Integer mFeedId;

    @SerializedName("n")
    private String mTitle;

    @SerializedName("l")
    private String mUrl;

    @SerializedName("e")
    private String mEncoding;

    public Integer getFeedId() {
        return mFeedId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getEncoding() {
        return mEncoding;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}