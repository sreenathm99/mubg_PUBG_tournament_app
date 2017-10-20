package me.toptas.rssreader.app;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import me.toptas.rssreader.model.RssItem;

public class SessionData {

    @Inject
    public SessionData() {
    }

    private final HashMap<String, List<RssItem>> mContentMap = new HashMap<>();

    public boolean hasUrl(String url) {
        return mContentMap.containsKey(url);
    }

    public void addContent(String url, List<RssItem> items) {
        mContentMap.put(url, items);
    }

    public List<RssItem> getContent(String url) {
        return mContentMap.get(url);
    }
}