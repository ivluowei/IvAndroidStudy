package cn.lw.study.widgets;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import cn.lw.study.R;
import cn.lw.study.core.BaseListActivity;

/**
 * Created by luow on 2016/9/29.
 */
public class pullToRefreshRecycleView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    public static final int ACTION_PULL_TO_REFRESH = 1;//上拉刷新
    public static final int ACTION_PULL_LOAD_MORE_REFRESH = 2;//下拉加载
    public static final int ACTION_IDLE = 0;//空闲
    private int mCurentState = ACTION_IDLE;
    private boolean isLoadMoreEnable = false;

    private ILayoutManager layoutManager;
    private BaseListActivity.BaseListAdapter adapter;

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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mCurentState == ACTION_IDLE && isLoadMoreEnable && checkIfLoadMore() && dy > 0) {
                    mCurentState = ACTION_PULL_LOAD_MORE_REFRESH;
                    swipeRefreshLayout.setEnabled(false);
                    adapter.showLoadMoreView(true);
                    listener.onRefresh(mCurentState);
                }
            }
        });
    }

    private boolean checkIfLoadMore() {
        int position = layoutManager.findLastItemPosition();//屏幕可见的最后一个item的position
        int totalCount = layoutManager.getLayoutManager().getItemCount();
        return totalCount - position < 5;
    }

    public void setLoadMoreEnable(boolean loadMoreEnable) {
        isLoadMoreEnable = loadMoreEnable;
    }

    public void setLayoutManager(ILayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        recyclerView.setLayoutManager(layoutManager.getLayoutManager());
    }

    public void setItemDecoreton(RecyclerView.ItemDecoration decoration) {
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
    }

    public void setAdapter(BaseListActivity.BaseListAdapter adapter) {
        this.adapter=adapter;
        recyclerView.setAdapter(adapter);
    }

    public void setRefreshView() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public void setRefreshCompleted() {
        switch (mCurentState) {
            case ACTION_PULL_TO_REFRESH:
                swipeRefreshLayout.setRefreshing(false);
                break;
            case ACTION_PULL_LOAD_MORE_REFRESH:
                swipeRefreshLayout.setEnabled(true);
                adapter.showLoadMoreView(false);
                break;
        }
        mCurentState = ACTION_IDLE;
    }


    @Override
    public void onRefresh() {
        mCurentState = ACTION_PULL_TO_REFRESH;
        listener.onRefresh(mCurentState);
    }

    public OnRefreshRecycleView listener;

    public void setOnRefreshRecycleView(OnRefreshRecycleView listener) {
        this.listener = listener;
    }

    public interface OnRefreshRecycleView {
        void onRefresh(int action);
    }
}
