package cn.lw.study.widgets.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.lw.study.R;
import cn.lw.study.core.ITabFragment;

/**
 * Created by luow on 2016/10/9.
 */
public class TabLayout extends LinearLayout implements View.OnClickListener {
    private ArrayList<Tab> tabs;
    private onTabClickListener listener;
    private View selectView;
    private int tabCount;

    public TabLayout(Context context) {
        super(context);
        setUpView(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView(context);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context);
    }

    private void setUpView(Context context) {
        setOrientation(HORIZONTAL);

    }

    public void setUpData(ArrayList<Tab> tabs, onTabClickListener listener) {
        this.tabs = tabs;
        this.listener = listener;


        if (tabs != null && tabs.size() > 0) {
            tabCount = tabs.size();
            TabView tabView;
            LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            for (int i = 0; i < tabs.size(); i++) {
                tabView = new TabView(getContext());
                tabView.setTag(tabs.get(i));
                tabView.setUpData(tabs.get(i));
                tabView.setOnClickListener(this);
                addView(tabView, params);
            }
        } else {
            new IllegalThreadStateException("tabs can't be null");
        }

    }

    public void setCurrentTab(int i) {
        if (i < tabCount && i >= 0) {
            View view = getChildAt(i);
            onClick(view);
        }
    }

    @Override
    public void onClick(View v) {
        if (selectView != v) {
            listener.onTabClick((Tab) v.getTag());
            v.setSelected(true);
            if (selectView != null) {
                selectView.setSelected(false);
            }
            selectView = v;
        }
    }

    public interface onTabClickListener {
        void onTabClick(Tab tabs);
    }


    public class TabView extends LinearLayout {

        private ImageView image;
        private TextView text;

        public TabView(Context context) {
            super(context);
            setUpView(context);
        }

        public TabView(Context context, AttributeSet attrs) {
            super(context, attrs);
            setUpView(context);
        }

        public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            setUpView(context);
        }

        private void setUpView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.widget_tab_view, this, true);
            setOrientation(VERTICAL);
            setGravity(Gravity.CENTER);
            image = (ImageView) view.findViewById(R.id.tab_image);
            text = (TextView) view.findViewById(R.id.tab_text);
        }

        public void setUpData(Tab tabs) {
            image.setBackgroundResource(tabs.imageResId);
            text.setText(tabs.textResId);
        }
    }

    public static class Tab {
        public int imageResId;
        public int textResId;
        public int menuResId;
        public int modeResId;
        public Class<? extends ITabFragment> targetFragmentClz;


        public Tab(int imageResId, int textResId) {
            this.imageResId = imageResId;
            this.textResId = textResId;
        }

        public Tab(int imageResId, int textResId, Class<? extends ITabFragment> targetFragmentClz) {
            this.imageResId = imageResId;
            this.textResId = textResId;
            this.targetFragmentClz = targetFragmentClz;
        }

        public Tab(int imageResId, int textResId, int menuResId, Class<? extends ITabFragment> targetFragmentClz) {
            this.imageResId = imageResId;
            this.textResId = textResId;
            this.menuResId = menuResId;
            this.targetFragmentClz = targetFragmentClz;
        }

        public Tab(int imageResId, int textResId, int menuResId, int modeResId, Class<? extends ITabFragment> targetFragmentClz) {
            this.targetFragmentClz = targetFragmentClz;
            this.imageResId = imageResId;
            this.textResId = textResId;
            this.menuResId = menuResId;
            this.modeResId = modeResId;
        }

    }

}
