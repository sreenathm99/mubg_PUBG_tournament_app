package me.mubg.tournament;

import android.app.Application;



import javax.inject.Inject;

import me.mubg.tournament.app.SessionData;
import me.mubg.tournament.di.module.ApplicationModule;
import me.mubg.tournament.di.component.ApplicationComponent;
import me.mubg.tournament.di.component.DaggerApplicationComponent;

public class RssApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Inject
    SessionData mSessionData;

    @Override
    public void onCreate() {
        super.onCreate();


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