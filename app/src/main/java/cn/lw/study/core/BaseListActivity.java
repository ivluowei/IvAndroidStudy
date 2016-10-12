package cn.lw.study.core;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import cn.lw.study.R;
import cn.lw.study.widgets.DividerItemDecoration;
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
        pullToRefreshRecycleView= (cn.lw.study.widgets.pullToRefreshRecycleView) findViewById(R.id.pullToRefreshRecycleView);
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
        return new DividerItemDecoration(getApplicationContext(),R.drawable.list_divider);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }


    public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return onCreateView(parent, viewType);
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            holder.onBindViewHolder(position);
        }

        @Override
        public int getItemCount() {
            return getDataCount();
        }
    }


    protected abstract BaseViewHolder onCreateView(ViewGroup parent, int viewType);

    protected abstract int getDataCount();


}
