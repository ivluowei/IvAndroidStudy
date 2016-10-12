package cn.lw.study.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.lw.study.R;
import cn.lw.study.adapter.FragmentAdapter;
import cn.lw.study.core.BaseFragment;
import cn.lw.study.core.ITabFragment;
import cn.lw.study.utils.ScreenUtils;

/**
 * Created by lw on 2016/6/21.
 */
public class MessageFragment extends BaseFragment implements View.OnClickListener,ITabFragment {
    private View view;
    private ViewPager viewPager;
    private ImageView imageViewLine;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private FragmentAdapter fragmentAdapter;
    private int currntPosition = 0;

    @Override
    protected int getLayooutId() {
        return R.layout.message_fragment;
    }

    @Override
    protected void setUpView() {
        viewPager = (ViewPager) getView().findViewById(R.id.message_viewpager);
        imageViewLine = (ImageView) getView().findViewById(R.id.line);
        tv1 = (TextView) getView().findViewById(R.id.text1);
        tv2 = (TextView) getView().findViewById(R.id.text2);
        tv3 = (TextView) getView().findViewById(R.id.text3);
        imageViewLine.getLayoutParams().width = ScreenUtils.getScreenWidth(getActivity()) / 3;
        inintListener();
        initPageView();
    }

    private void initPageView() {
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        list.add(new ViewPagerFragment());
        list.add(new ViewPagerFragment());
        list.add(new ViewPagerFragment());
        for (int i = 0; i < list.size(); i++) {
            fragmentAdapter = new FragmentAdapter(getFragmentManager(), list);
            viewPager.setAdapter(fragmentAdapter);
        }
        viewPager.setCurrentItem(currntPosition);
    }

    private void inintListener() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TranslateAnimation ta = new TranslateAnimation(currntPosition * ScreenUtils.getScreenWidth(getActivity()) / 3, position * ScreenUtils.getScreenWidth(getActivity()) / 3, 0, 0);
                ta.setDuration(100);
                ta.setFillAfter(true);
                imageViewLine.startAnimation(ta);
                currntPosition = position;
                tv1.setTextColor(getResources().getColor(R.color.black));
                tv2.setTextColor(getResources().getColor(R.color.black));
                tv3.setTextColor(getResources().getColor(R.color.black));
                switch (position) {
                    case 0:
                        tv1.setTextColor(getResources().getColor(R.color.blue));
                        break;
                    case 1:
                        tv2.setTextColor(getResources().getColor(R.color.blue));

                        break;

                    case 2:
                        tv3.setTextColor(getResources().getColor(R.color.blue));

                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.text2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.text3:
                viewPager.setCurrentItem(2);
                break;
        }

    }

    @Override
    public void onMenuItemClick(MenuItem item) {
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}
