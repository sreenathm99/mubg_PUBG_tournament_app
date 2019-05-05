package me.mubg.tournament.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.mubg.tournament.RssApplication;
import me.mubg.tournament.di.module.ApplicationModule;

/**
 * Created by faruktoptas on 05/02/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(RssApplication rssApplication);

}
