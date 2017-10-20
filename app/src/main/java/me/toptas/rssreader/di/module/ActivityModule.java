package me.toptas.rssreader.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.toptas.rssreader.chrome.ChromeTabsWrapper;
import me.toptas.rssreader.main.MainContract;
import me.toptas.rssreader.main.MainPresenter;

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