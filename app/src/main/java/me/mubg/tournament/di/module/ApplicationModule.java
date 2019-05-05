package me.mubg.tournament.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mubg.tournament.app.SessionData;
import me.mubg.tournament.RssApplication;

/**
 * Created by faruktoptas on 05/02/17.
 */

@Module
public class ApplicationModule {

    private final RssApplication mApplication;

    public ApplicationModule(RssApplication application) {
        mApplication = application;
    }

    @Provides
    RssApplication providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SessionData providesSessionData() {
        return new SessionData();
    }

}
