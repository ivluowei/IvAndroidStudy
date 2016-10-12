package cn.lw.study;

import android.app.Application;

import cn.lw.study.utils.AppStatusTracker;

/**
 * Created by luow on 2016/9/26.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        AppStatusTracker.init();
    }
}
