package cn.lw.study.core;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.lw.study.R;
import cn.lw.study.widgets.DividerItemDecoration;
import cn.lw.study.widgets.ILayoutManager;
import cn.lw.study.widgets.MyLinearLayoutManager;
import cn.lw.study.widgets.pullToRefreshRecycleView;

/**
 * Created by luow on 2016/9/27.
 */
public abstract class BaseListActivity extends BaseActivity implements cn.lw.study.widgets.pullToRefreshRecycleView.OnRefreshRecycleView {
    protected pullToRefreshRecycleView pullToRefreshRecycleView;
    protected BaseListAdapter adapter;

    @Override
    protected void setUpContentView() {

    }

    @Override
    protected void setUpView() {
        pullToRefreshRecycleView = (cn.lw.study.widgets.pullToRefreshRecycleView) findViewById(R.id.pullToRefreshRecycleView);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        adapter = new BaseListAdapter();
        pullToRefreshRecycleView.setLayoutManager(getLayoutManager());
        pullToRefreshRecycleView.setItemDecoreton(geteItemDecoreton());
        pullToRefreshRecycleView.setAdapter(adapter);
        pullToRefreshRecycleView.setOnRefreshRecycleView(this);
    }

    @Override
    public void onRefresh(int action) {

    }

    protected RecyclerView.ItemDecoration geteItemDecoreton() {
        return new DividerItemDecoration(getApplicationContext(), R.drawable.list_divider);
    }

    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(this);
    }


    public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

        private static final int VIEW_TYPE_LOAD_MORE_FOOTER = 100;
        private boolean isLoadMoreShow;

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_LOAD_MORE_FOOTER) {
                return getLoadMoreView(parent);
            }
            return onCreateView(parent, viewType);
        }

        private BaseViewHolder getLoadMoreView(ViewGroup parent) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.widget_pull_to_refresh_footer, parent, false);
            return new LoadMoreViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            //StaggeredGridLayoutManager 通过setFullSpan设置占一行
            if (isLoadMoreShow && position == getItemCount() - 1) {
                if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                    ((StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams()).setFullSpan(true);
                }
            }
            holder.onBindViewHolder(position);
        }

        @Override
        public int getItemViewType(int position) {
            if (isLoadMoreShow && position == getItemCount() - 1) {
                return VIEW_TYPE_LOAD_MORE_FOOTER;
            }
            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return getDataCount() + (isLoadMoreShow ? 1 : 0);
        }

        //是否显示加载更多view
        public void showLoadMoreView(boolean isShow) {
            isLoadMoreShow = isShow;
            if (isShow) {
                notifyItemInserted(getItemCount());
            } else {
                notifyItemRemoved(getItemCount());
            }
        }

        //GridLayoutManager通过设置SpanSizeLookup来让其load view占一行
        public boolean isFootView(int position) {
            if (isLoadMoreShow && position == getItemCount() - 1) {
                return true;
            }
            return false;
        }

        private class LoadMoreViewHolder extends BaseViewHolder {
            public LoadMoreViewHolder(View view) {
                super(view);
            }

            @Override
            public void onBindViewHolder(int position) {

            }

            @Override
            public void onItemClick(View v, int position) {

            }
        }
    }

    protected abstract BaseViewHolder onCreateView(ViewGroup parent, int viewType);

    protected abstract int getDataCount();


}
