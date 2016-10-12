package cn.lw.study.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by luow on 2016/6/22.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentsList;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
        super(fm);
        this.fragmentsList = fragmentsList;
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return (fragmentsList == null || fragmentsList.size() == 0) ? null : fragmentsList.get(position);
    }

}
