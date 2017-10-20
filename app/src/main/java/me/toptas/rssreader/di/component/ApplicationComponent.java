package me.toptas.rssreader.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.toptas.rssreader.RssApplication;
import me.toptas.rssreader.di.module.ApplicationModule;

/**
 * Created by faruktoptas on 05/02/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(RssApplication rssApplication);

}
