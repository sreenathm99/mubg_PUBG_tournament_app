package me.toptas.jobseasy.di.module;

import dagger.Module;
import dagger.Provides;
import me.toptas.jobseasy.app.SessionData;
import me.toptas.jobseasy.rss.RssContract;
import me.toptas.jobseasy.rss.RssPresenter;

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
