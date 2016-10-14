package cn.lw.study.widgets;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by luow on 2016/10/14.
 */
public class MyGridLayoutanager extends GridLayoutManager implements ILayoutManager {
    public MyGridLayoutanager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyGridLayoutanager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MyGridLayoutanager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int findLastItemPosition() {
        return findLastVisibleItemPosition();
    }
}
