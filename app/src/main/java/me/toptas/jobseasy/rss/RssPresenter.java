package me.toptas.jobseasy.rss;

import java.util.List;

import javax.inject.Inject;

import me.toptas.jobseasy.app.SessionData;
import me.toptas.jobseasy.base.BasePresenter;
import me.toptas.jobseasy.model.Feed;
import me.toptas.jobseasy.model.RError;
import me.toptas.jobseasy.model.RssItem;
import me.toptas.jobseasy.parser.OnRssParserListener;
import me.toptas.jobseasy.parser.RssReader;

/**
 * Created by faruktoptas on 29/01/17.
 */

public class RssPresenter extends BasePresenter<RssContract.View> implements RssContract.Presenter, OnRssParserListener {

    private SessionData mSessionData;

    @Inject
    public RssPresenter(SessionData sessionData) {
        mSessionData = sessionData;
    }

    @Override
    public void loadRssItems(Feed feed, boolean fromCache) {
        if (mSessionData.hasUrl(feed.getUrl()) && fromCache) {
            getView().onRssItemsLoaded(mSessionData.getContent(feed.getUrl()));
        } else {
            RssReader request = new RssReader(this, feed.getUrl());
            request.execute();
            getView().showLoading();
        }
    }

    @Override
    public void browseRssUrl(RssItem rssItem) {
        if (isAttached()) {
            getView().onBrowse(rssItem);
        }
    }

    @Override
    public void onSuccess(List<RssItem> rssItemList, String rssUrl) {
        mSessionData.addContent(rssUrl, rssItemList);
        if (isAttached()) {
            getView().onRssItemsLoaded(rssItemList);
            getView().hideLoading();
        }
    }

    @Override
    public void onFail(String rssUrl) {
        if (isAttached()) {
            getView().hideLoading();
            getView().onFail(new RError("Failed to fetch RSS!"));
        }
    }

    public SessionData getSessionData() {
        return mSessionData;
    }
}
