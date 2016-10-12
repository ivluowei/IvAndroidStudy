package cn.lw.study.fragment;

import android.view.MenuItem;
import android.view.View;

import cn.lw.study.R;
import cn.lw.study.core.BaseFragment;
import cn.lw.study.core.ITabFragment;
import cn.lw.study.utils.ToastUtils;
import cn.lw.study.widgets.tab.TabLayout;

/**
 * Created by lw on 2016/6/21.
 */
public class SettingFragment extends BaseFragment implements ITabFragment{
    private View view;
    private TabLayout tabLayout;

    @Override
    protected int getLayooutId() {
        return R.layout.setting_fragment;
    }

    @Override
    protected void setUpView() {
        tabLayout= (TabLayout) getView().findViewById(R.id.tablayout);
    }

    @Override
    public void onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings:
                ToastUtils.showShort(getActivity(),"啦啦啦阿里");
                break;
        }
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
