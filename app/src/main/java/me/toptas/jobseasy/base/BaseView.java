package me.toptas.jobseasy.base;

/**
 * Created by faruktoptas on 28/01/17.
 */

import me.toptas.jobseasy.model.RError;

/**
 * Each view should extend from this interface
 */
public interface BaseView {
    void onFail(RError error);
}
