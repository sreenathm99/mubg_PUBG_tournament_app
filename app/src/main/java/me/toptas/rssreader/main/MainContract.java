package me.toptas.rssreader.main;

import me.toptas.rssreader.base.BaseMvpPresenter;
import me.toptas.rssreader.base.BaseView;

/**
 * Created by faruktoptas on 28/01/17.
 */

public interface MainContract {

    // User actions. Presenter will implement
    interface Presenter extends BaseMvpPresenter<MainContract.View>{
        void loadRssFragments();
    }

    // Action callbacks. Activity/Fragment will implement
    interface View extends BaseView {
        void onLoadRssFragments();
    }

}
