package cn.lw.study.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.lw.study.R;
import cn.lw.study.activity.FirstActivity;
import cn.lw.study.activity.SecondActivity;
import cn.lw.study.adapter.FragmentAdapter;
import cn.lw.study.core.BaseFragment;
import cn.lw.study.core.ITabFragment;
import cn.lw.study.utils.ScreenUtils;

/**
 * Created by lw on 2016/6/21.
 */
public class JokeFragment extends BaseFragment  implements ITabFragment {
    private ImageView mSlideLine;
    private ViewPager mViewPager;
    private FragmentAdapter adapter;
    private View view;
    private TextView rbLabel;
    private int labelWidth;
    private ArrayList<String> data;
    private RadioGroup radioGroup;
    private int currntPosition = 0;
    private HorizontalScrollView horizontalScrollView;

    @Override
    protected int getLayooutId() {
        return R.layout.joke_fragment;
    }

    @Override
    protected void setUpView() {
        labelWidth = ScreenUtils.getScreenWidth(getActivity()) / 4;
        radioGroup = (RadioGroup) getView().findViewById(R.id.radioGroup);
        horizontalScrollView = (HorizontalScrollView) getView().findViewById(R.id.horizontalScrollView);
        data = new ArrayList<String>();
        data.add("首页首页");
        data.add("新闻首页");
        data.add("周刊首页");
        data.add("周刊首页");
        data.add("爱我");
        mSlideLine = (ImageView) getView().findViewById(R.id.iv_slide_line);
        mViewPager = (ViewPager) getView().findViewById(R.id.viewPager);
        initTab(data);
        initPageView();
        initListener();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("isVisibleToUser","222"+hidden);
    }

    private void initListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (radioGroup != null && radioGroup.getChildCount() > position) {
                    ((RadioButton) radioGroup.getChildAt(position)).performClick();//触发点击事件
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioGroup.getChildAt(checkedId) != null) {
                    TranslateAnimation ta = new TranslateAnimation(currntPosition * labelWidth, checkedId * labelWidth, 0, 0);
                    ((RadioButton) radioGroup.getChildAt(currntPosition)).setTextColor(0xff494949);//未选中颜色
                    ((RadioButton) radioGroup.getChildAt(checkedId)).setTextColor(getResources().getColor(R.color.blue));//选中颜色
                    ta.setDuration(100);
                    ta.setFillAfter(true);
                    mSlideLine.startAnimation(ta);
                    mViewPager.setCurrentItem(checkedId);
                    currntPosition = checkedId;
                    horizontalScrollView.smoothScrollTo((checkedId > 1 ? ((RadioButton) radioGroup.getChildAt(checkedId)).getLeft() : 0)
                            - ((RadioButton) radioGroup.getChildAt(1)).getLeft(), 0);

                }
            }
        });
    }

    private RadioButton initRadioButton(String text, int id) {
        RadioButton rb = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.item_rb, null);
        rb.setId(id);
        rb.setText(text);
        rb.setLayoutParams(new ViewGroup.LayoutParams((int) labelWidth, ViewGroup.LayoutParams.FILL_PARENT));
        return rb;
    }

    private void initTab(ArrayList<String> data) {
        radioGroup.removeAllViews();
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                rbLabel = initRadioButton(data.get(i), i);
                radioGroup.addView(rbLabel);
            }
        }

        mSlideLine.getLayoutParams().width=labelWidth;
        // 计算偏移量
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.key_word_slide_line);
//        float imgWidth = bitmap.getWidth();
        float dx = 0f;
        if (ScreenUtils.getScreenWidth(getActivity()) == 480) {
            dx = 12f;
        } else if (ScreenUtils.getScreenWidth(getActivity()) == 720) {
            dx = 36f;
        } else if (ScreenUtils.getScreenWidth(getActivity()) == 1080) {
            dx = 48f;
        }
      // float offset = labelWidth / 2;
        // 设置偏移位置
      //  Matrix matrix = new Matrix();
       // matrix.postTranslate(offset, 0);
      //  mSlideLine.setImageMatrix(matrix);
    }

    private void initPageView() {
        ArrayList<Fragment> arrayList = new ArrayList<Fragment>();
        for (int i = 0; i < data.size(); i++) {
            ViewPagerFragment fragment = new ViewPagerFragment();
            arrayList.add(fragment);
        }
        adapter = new FragmentAdapter(getFragmentManager(), arrayList);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(getActivity(), FirstActivity.class));
                break;
            case R.id.action_notification:
                break;
            case R.id.action_settings:
                startActivity(new Intent(getActivity(), SecondActivity.class));
                break;
            case R.id.action_about:
                break;
        }
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
}