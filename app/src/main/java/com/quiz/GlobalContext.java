
package com.quiz;

import android.app.Application;

public class GlobalContext extends Application {


    private static GlobalContext instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized GlobalContext getInstance() {
        if (instance == null) {
            instance = new GlobalContext();
        }
        return instance;
    }

}
