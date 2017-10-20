package me.toptas.jobseasy.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.toptas.jobseasy.RssApplication;
import me.toptas.jobseasy.di.module.ApplicationModule;

/**
 * Created by faruktoptas on 05/02/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(RssApplication rssApplication);

}
