package cn.lw.study.activity;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import cn.lw.study.R;
import cn.lw.study.core.BaseActivity;
import cn.lw.study.core.ITabFragment;
import cn.lw.study.fragment.FuningFragment;
import cn.lw.study.fragment.JokeFragment;
import cn.lw.study.fragment.MessageFragment;
import cn.lw.study.widgets.tab.TabLayout;

/**
 * Created by luow on 2016/10/10.
 */
public class SimpleActivity extends BaseActivity implements TabLayout.onTabClickListener {
    private TabLayout tabLayout;
    private ArrayList<TabLayout.Tab> tabs;
    private ITabFragment fragment;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_simple, R.string.title, R.menu.draw_item, MODE_BACK);
    }

    @Override
    protected void setUpView() {
        tabLayout = (TabLayout) findViewById(R.id.mTabLayout);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        tabs = new ArrayList<TabLayout.Tab>();
        tabs.add(new TabLayout.Tab(R.drawable.joke_selector, R.string.joke, R.menu.main, JokeFragment.class));
        tabs.add(new TabLayout.Tab(R.drawable.funng_selector, R.string.funing,R.menu.draw_item, FuningFragment.class));
        tabs.add(new TabLayout.Tab(R.drawable.message_selector, R.string.message,R.menu.draw_item,MessageFragment.class));
      //  tabs.add(new TabLayout.Tab(R.drawable.setting_selector, R.string.setting, R.menu.main, SettingFragment.class));
        tabLayout.setUpData(tabs, this);
        tabLayout.setCurrentTab(0);
    }

    @Override
    public void onTabClick(TabLayout.Tab tab) {
        setUpTitle(tab.textResId);
        setUpMenu(tab.menuResId);
        try {
            fragment = tab.targetFragmentClz.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.mFragmentContainerLayout, fragment.getFragment()).commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        fragment.onMenuItemClick(item);
        return super.onMenuItemClick(item);
    }
}
