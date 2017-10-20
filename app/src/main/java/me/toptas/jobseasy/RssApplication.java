package me.toptas.jobseasy;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import me.toptas.jobseasy.app.SessionData;
import me.toptas.jobseasy.di.component.ApplicationComponent;
import me.toptas.jobseasy.di.component.DaggerApplicationComponent;
import me.toptas.jobseasy.di.module.ApplicationModule;

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