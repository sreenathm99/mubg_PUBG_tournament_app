package me.toptas.rssreader.di.module;

import dagger.Module;
import dagger.Provides;
import me.toptas.rssreader.app.SessionData;
import me.toptas.rssreader.rss.RssContract;
import me.toptas.rssreader.rss.RssPresenter;

/**
 * Created by faruktoptas on 29/01/17.
 */

@Module
public class FragmentModule {

    @Provides
    RssContract.Presenter providesRssPresenter(SessionData sessionData) {
        return new RssPresenter(sessionData);
    }
}
