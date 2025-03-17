package com.vladyslav.plugins.location.state;

import android.util.Log;

public class LocationState {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
