package cn.lw.study.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import cn.lw.study.MainActivity;
import cn.lw.study.R;
import cn.lw.study.constants.ConstantsValue;
import cn.lw.study.core.BaseActivity;
import cn.lw.study.utils.AppStatusTracker;

/**
 * Created by luow on 2016/9/26.
 */
public class WelcomeActivity extends BaseActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusTracker.getInstance().setAppStatus(ConstantsValue.STATUS_OFFLINE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_welcome, -1, MODE_NONE);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(0);
    }
}
