package me.mubg.tournament.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mubg.tournament.chrome.ChromeTabsWrapper;
import me.mubg.tournament.main.MainContract;
import me.mubg.tournament.main.MainPresenter;

@Module
public class ActivityModule {

    private Context mContext;

    public ActivityModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    ChromeTabsWrapper providesChromeTabsWrapper() {
        return new ChromeTabsWrapper(mContext);
    }

    @Provides
    @Singleton
    MainContract.Presenter providesMainPresenter() {
        return new MainPresenter();
    }

}