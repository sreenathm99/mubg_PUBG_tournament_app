package me.mubg.tournament.di.module;

import dagger.Module;
import dagger.Provides;
import me.mubg.tournament.app.SessionData;
import me.mubg.tournament.rss.RssContract;
import me.mubg.tournament.rss.RssPresenter;

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
