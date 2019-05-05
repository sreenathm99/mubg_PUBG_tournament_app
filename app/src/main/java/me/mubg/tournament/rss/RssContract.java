package me.mubg.tournament.rss;

import java.util.List;

import me.mubg.tournament.base.AsyncCallbackView;
import me.mubg.tournament.base.BaseMvpPresenter;
import me.mubg.tournament.base.BaseView;
import me.mubg.tournament.model.Feed;
import me.mubg.tournament.model.RssItem;

/**
 * Created by faruktoptas on 29/01/17.
 */

public interface RssContract {

    // User roomcardback. Presenter will implement
    interface Presenter extends BaseMvpPresenter<View> {
        void loadRssItems(Feed feed, boolean fromCache);
        void browseRssUrl(RssItem rssItem);
    }

    // Action callbacks. Activity/Fragment will implement
    interface View extends BaseView, AsyncCallbackView {
        void onRssItemsLoaded(List<RssItem> rssItems);
        void onBrowse(RssItem rssItem);
    }
}
