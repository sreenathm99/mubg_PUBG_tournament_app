package me.toptas.rssreader.model;

/**
 * Created by faruktoptas on 29/01/17.
 */

public class RError {

    public static final String ERROR_FETCH = "Failed to fetch RSS!";

    private final String mMessage;

    public RError(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}
