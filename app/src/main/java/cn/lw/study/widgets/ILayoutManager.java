package cn.lw.study.widgets;

import android.support.v7.widget.RecyclerView;

/**
 * Created by luow on 2016/10/14.
 */
public interface ILayoutManager {
    RecyclerView.LayoutManager getLayoutManager();
    int findLastItemPosition();
}
