package cn.lw.study.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import cn.lw.study.R;
import cn.lw.study.core.BaseFragment;
import cn.lw.study.core.ITabFragment;

/**
 * Created by lw on 2016/6/21.
 */
public class FuningFragment extends BaseFragment implements ITabFragment {
    private View view;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentAdapter adapter;
    private ArrayList<Fragment> fragmentsList;
    private ArrayList<String> list;

    @Override
    protected int getLayooutId() {
        return R.layout.funing_fragment;
    }

    @Override
    protected void setUpView() {
        mTabLayout = (TabLayout) getView().findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) getView().findViewById(R.id.viewPager);
        fragmentsList = new ArrayList<>();
        fragmentsList.add(new SettingFragment());
        fragmentsList.add(new SettingFragment());
        fragmentsList.add(new SettingFragment());
        fragmentsList.add(new SettingFragment());
        fragmentsList.add(new SettingFragment());
        fragmentsList.add(new SettingFragment());
        fragmentsList.add(new SettingFragment());
        fragmentsList.add(new SettingFragment());
        fragmentsList.add(new SettingFragment());

        list = new ArrayList<>();
        list.add("热门推");
        list.add("热门推");
        list.add("热门收藏");
        list.add("热门推");
        list.add("热门收藏");
        list.add("热榜");
        list.add("热门推");
        list.add("热榜");
        list.add("热门收藏");


        //设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //为TabLayout添加tab名称
        mTabLayout.addTab(mTabLayout.newTab().setText(list.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list.get(2)));
        adapter = new FragmentAdapter(getChildFragmentManager());

        mViewPager.setAdapter(adapter);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("isVisibleToUser","333"+isVisibleToUser);
    }

    @Override
    public void onMenuItemClick(MenuItem item) {

    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }

    class FragmentAdapter extends FragmentPagerAdapter {


        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return (fragmentsList == null || fragmentsList.size() == 0) ? null : fragmentsList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position % list.size());
        }
    }

}
