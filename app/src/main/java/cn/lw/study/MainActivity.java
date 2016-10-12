package cn.lw.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;

import java.util.ArrayList;

import cn.lw.study.activity.WelcomeActivity;
import cn.lw.study.constants.ConstantsValue;
import cn.lw.study.core.BaseActivity;
import cn.lw.study.core.ITabFragment;
import cn.lw.study.fragment.FuningFragment;
import cn.lw.study.fragment.JokeFragment;
import cn.lw.study.fragment.MessageFragment;
import cn.lw.study.fragment.SettingFragment;
import cn.lw.study.widgets.tab.TabLayout;

public class MainActivity extends BaseActivity implements TabLayout.onTabClickListener {
    private TabLayout tabLayout;
    private ArrayList<TabLayout.Tab> tabs;
    private ITabFragment fragment;
    private DrawerLayout drawer;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_main, R.string.title, R.menu.draw_item, MODE_DRAWER);
    }

    @Override
    protected void setUpView() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        tabs = new ArrayList<TabLayout.Tab>();
        tabs.add(new TabLayout.Tab(R.drawable.joke_selector, R.string.joke, R.menu.draw_item, MODE_DRAWER, JokeFragment.class));
        tabs.add(new TabLayout.Tab(R.drawable.funng_selector, R.string.funing, R.menu.main, MODE_BACK, FuningFragment.class));
        tabs.add(new TabLayout.Tab(R.drawable.message_selector, R.string.message, R.menu.draw_item, MODE_NONE, MessageFragment.class));
        tabs.add(new TabLayout.Tab(R.drawable.setting_selector, R.string.setting, R.menu.main, MODE_DRAWER, SettingFragment.class));
        tabs.add(new TabLayout.Tab(R.drawable.setting_selector, R.string.setting, R.menu.main, MODE_DRAWER, SettingFragment.class));
        tabLayout.setUpData(tabs, this);
        tabLayout.setCurrentTab(0);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        fragment.onMenuItemClick(item);
        return super.onMenuItemClick(item);
    }

    @Override
    protected void onNavigationBtnClick() {
        drawer.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onTabClick(TabLayout.Tab tab) {

        setUpToolbar(tab.textResId, tab.menuResId, tab.modeResId);

        if (tab.modeResId == MODE_NONE) {
                toolbar.setNavigationIcon(null);
        }
        try {
            fragment = tab.targetFragmentClz.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment.getFragment()).commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        switch (intent.getIntExtra("appStatus", 0)) {
            case ConstantsValue.STATUS_RESTART_APP:
                protectApp();
                break;
        }
    }

    @Override
    protected void protectApp() {
        startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApplication(this);
        }
        return false;
    }

}
