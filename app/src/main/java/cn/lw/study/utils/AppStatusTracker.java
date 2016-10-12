package cn.lw.study.utils;

import cn.lw.study.constants.ConstantsValue;

/**
 * Created by luow on 2016/9/26.
 */
public class AppStatusTracker {
    public static AppStatusTracker tracker;
    private int appStatus= ConstantsValue.STATUS_FORCE_KILLED;

    public int getAppStatus() {
        return appStatus;
    }

    public static void init(){
        tracker=new AppStatusTracker();
    }

    public static AppStatusTracker getInstance() {
        return tracker;
    }

    public void setAppStatus(int status) {
        this.appStatus=status;
    }
}
