package me.toptas.jobseasy.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.toptas.jobseasy.RssApplication;
import me.toptas.jobseasy.app.SessionData;

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
