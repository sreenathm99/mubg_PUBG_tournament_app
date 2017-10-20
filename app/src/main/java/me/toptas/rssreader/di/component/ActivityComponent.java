package me.toptas.rssreader.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.toptas.rssreader.di.module.ActivityModule;
import me.toptas.rssreader.main.MainActivity;

@Singleton
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity obj);

}