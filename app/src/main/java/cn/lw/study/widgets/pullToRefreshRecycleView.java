package cn.lw.study.widgets;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import cn.lw.study.R;

/**
 * Created by luow on 2016/9/29.
 */
public class pullToRefreshRecycleView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    public pullToRefreshRecycleView(Context context) {
        super(context);
        setUpView();
    }


    public pullToRefreshRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public pullToRefreshRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    private void setUpView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_refresh, this, true);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setItemDecoreton(RecyclerView.ItemDecoration decoration) {
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void setRefreshView() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                listener.onRefresh(0);
            }
        });
    }

    public void setStopRefreshView() {
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        listener.onRefresh(0);
    }

    public OnRefreshRecycleView listener;

    public void setOnRefreshRecycleView(OnRefreshRecycleView listener) {
        this.listener = listener;
    }

    public interface OnRefreshRecycleView {
        void onRefresh(int action);
    }
}
