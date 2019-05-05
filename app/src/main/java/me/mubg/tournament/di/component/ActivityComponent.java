package me.mubg.tournament.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.mubg.tournament.di.module.ActivityModule;
import me.mubg.tournament.main.MainActivity;

@Singleton
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity obj);

}