package me.toptas.rssreader;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import me.toptas.rssreader.app.SessionData;
import me.toptas.rssreader.di.component.ApplicationComponent;
import me.toptas.rssreader.di.component.DaggerApplicationComponent;
import me.toptas.rssreader.di.module.ApplicationModule;

public class RssApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Inject
    SessionData mSessionData;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public SessionData getSessionData() {
        return mSessionData;
    }
}