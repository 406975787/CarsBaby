package app.weather.com.textndk;

import android.app.Application;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by mengxj on 2018/5/15.
 */

public class CarsApplication extends Application {

    public static CarsApplication instance;
    public JCVideoPlayerStandard VideoPlaying;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
