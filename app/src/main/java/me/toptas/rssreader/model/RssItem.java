package me.toptas.rssreader.model;

public class RssItem {

    private String mTitle;


    private String mUrl;


    private String mImageUrl;


    private String mPubDate;


    private int mFeedId;

    private String mFeedTitle;

    private String mDescription;

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public void setPubDate(String pubDate) {
        mPubDate = pubDate;
    }

    public void setFeedId(int feedId) {
        mFeedId = feedId;
    }

    public void setFeedTitle(String feedTitle) {
        mFeedTitle = feedTitle;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public int getFeedId() {
        return mFeedId;
    }

    public String getFeedTitle() {
        return mFeedTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}