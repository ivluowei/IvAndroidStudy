package cn.lw.study.widgets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by luow on 2016/10/14.
 */
public class MyStaggeredGridLayoutManager extends StaggeredGridLayoutManager implements ILayoutManager{
    public MyStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int findLastItemPosition() {
        int[] positions=null;
        positions=findLastVisibleItemPositions(positions);
        return positions[0];
    }
}
