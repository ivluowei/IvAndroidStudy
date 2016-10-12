package cn.lw.study.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import cn.lw.study.MainActivity;
import cn.lw.study.R;
import cn.lw.study.constants.ConstantsValue;
import cn.lw.study.utils.AppStatusTracker;
import cn.lw.study.utils.ToastUtils;

/**
 * Created by lw on 2016/6/20.
 */
public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private static long lastTimeStamp = 0l;
    protected Toolbar toolbar;
    protected TextView tv_title;
    public static final int MODE_BACK = 0;
    public static final int MODE_DRAWER = 1;
    public static final int MODE_NONE = 2;
    public static final int MODE_HOME = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (AppStatusTracker.getInstance().getAppStatus()) {
            case ConstantsValue.STATUS_FORCE_KILLED:
                protectApp();
                break;
            case ConstantsValue.STATUS_LOGOUT:
            case ConstantsValue.STATUS_OFFLINE:
            case ConstantsValue.STATUS_ONLINE:
                setUpContentView();
                setUpView();
                setUpData(savedInstanceState);
                break;
        }
    }

    protected void protectApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("appStatus", ConstantsValue.STATUS_RESTART_APP);
        startActivity(intent);
    }

    protected abstract void setUpContentView();

    protected abstract void setUpView();

    protected abstract void setUpData(Bundle savedInstanceState);

    @Override
    public void setContentView(int layoutResID) {
        setContentView(layoutResID, -1, -1, MODE_BACK);
    }

    protected void setContentView(int layoutResID, int titleId) {
        setContentView(layoutResID, titleId, -1, MODE_BACK);
    }

    protected void setContentView(int layoutResID, int titleId, int mode) {
        setContentView(layoutResID, titleId, -1, mode);
    }

    protected void setContentView(int layoutResID, int titleId, int menuId, int mode) {
        super.setContentView(layoutResID);
        setUpToolbar(titleId, menuId, mode);
    }

    protected void setUpToolbar(int titleId, int menuId, int mode) {
        if (mode != MODE_NONE) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            tv_title = (TextView) findViewById(R.id.toolbar_title);
            //toolbar.setTitle("");
            if (mode == MODE_BACK) {
                toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
            } else if (mode == MODE_DRAWER) {
                toolbar.setNavigationIcon(R.drawable.ic_drawer_home);
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationBtnClick();
                }
            });
            setUpTitle(titleId);
            setUpMenu(menuId);
        }
    }

    protected void setUpMenu(int menuId) {
        if (toolbar != null) {
            toolbar.getMenu().clear();
            if (menuId > 0) {
                toolbar.inflateMenu(menuId);
                toolbar.setOnMenuItemClickListener(this);
            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    protected void setUpTitle(int titleId) {
        if (titleId > 0 && tv_title != null) {
            tv_title.setText(titleId);
        }
    }

    protected void onNavigationBtnClick() {
        finish();
    }

    protected void showToast(Activity paramActivity, String paramString) {
        if (!isFinishing())
            ToastUtils.showToast(paramActivity, paramString);
    }

    /**
     * 退出程序.
     *
     * @param context
     */
    public static void exitApplication(Activity context) {
        long currentTimeStamp = System.currentTimeMillis();
        if (currentTimeStamp - lastTimeStamp > 1350L) {
            ToastUtils.showToast(context, "再按一次退出");
        } else {
            context.finish();
        }
        lastTimeStamp = currentTimeStamp;
    }

}
