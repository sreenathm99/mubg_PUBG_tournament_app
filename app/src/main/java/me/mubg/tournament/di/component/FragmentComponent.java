package me.mubg.tournament.di.component;

import dagger.Component;
import me.mubg.tournament.di.module.FragmentModule;
import me.mubg.tournament.rss.RssFragment;

/**
 * Created by faruktoptas on 29/01/17.
 */

@Component(modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(RssFragment obj);

}



