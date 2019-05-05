package me.mubg.tournament.main;

import me.mubg.tournament.base.BasePresenter;

/**
 * Created by faruktoptas on 28/01/17.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void loadRssFragments() {
        getView().onLoadRssFragments();
    }
}
