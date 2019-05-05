package me.mubg.tournament.main;

import me.mubg.tournament.base.BaseMvpPresenter;
import me.mubg.tournament.base.BaseView;

/**
 * Created by faruktoptas on 28/01/17.
 */

public interface MainContract {

    // User roomcardback. Presenter will implement
    interface Presenter extends BaseMvpPresenter<View> {
        void loadRssFragments();
    }

    // Action callbacks. Activity/Fragment will implement
    interface View extends BaseView {
        void onLoadRssFragments();
    }

}
