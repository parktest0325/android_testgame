package lol.dongkim.testgame;

import android.app.Application;

import com.google.android.gms.games.PlayGamesSdk;

public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PlayGamesSdk.initialize(this);
    }
}