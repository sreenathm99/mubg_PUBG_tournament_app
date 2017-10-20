package me.toptas.jobseasy.di.component;

import dagger.Component;
import me.toptas.jobseasy.di.module.FragmentModule;
import me.toptas.jobseasy.rss.RssFragment;

/**
 * Created by faruktoptas on 29/01/17.
 */

@Component(modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(RssFragment obj);

}



