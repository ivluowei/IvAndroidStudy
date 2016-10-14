package cn.lw.study.widgets;

import android.support.v7.widget.GridLayoutManager;

import cn.lw.study.core.BaseListActivity;

/**
 * Created by luow on 2016/10/14.
 */
public class GridSpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {
    private BaseListActivity.BaseListAdapter adapter;
    private int spanCount;

    public GridSpanSizeLookUp(BaseListActivity.BaseListAdapter adapter, int spanCount) {
        this.adapter = adapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if(adapter.isFootView(position)){
            return spanCount;
        }
        return 1;
    }
}
