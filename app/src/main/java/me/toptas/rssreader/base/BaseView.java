package me.toptas.rssreader.base;

/**
 * Created by faruktoptas on 28/01/17.
 */

import me.toptas.rssreader.model.RError;

/**
 * Each view should extend from this interface
 */
public interface BaseView {
    void onFail(RError error);
}
