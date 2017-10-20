package me.toptas.jobseasy.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.toptas.jobseasy.di.module.ActivityModule;
import me.toptas.jobseasy.main.MainActivity;

@Singleton
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity obj);

}