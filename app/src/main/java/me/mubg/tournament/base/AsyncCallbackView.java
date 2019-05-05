package me.mubg.tournament.base;

/**
 * Created by faruktoptas on 05/02/17.
 * Interface for hiding/showing loading indicator in async operations
 */

public interface AsyncCallbackView {
    void showLoading();

    void hideLoading();
}
