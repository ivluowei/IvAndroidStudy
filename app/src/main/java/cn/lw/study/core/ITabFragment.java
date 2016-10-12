package cn.lw.study.core;


import android.view.MenuItem;

public interface ITabFragment {

    void onMenuItemClick(MenuItem item);

    BaseFragment getFragment();
}
