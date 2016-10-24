package cn.lw.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.lw.study.MainActivity;
import cn.lw.study.R;
import cn.lw.study.constants.ConstantsValue;
import cn.lw.study.core.BaseListActivity;
import cn.lw.study.core.BaseViewHolder;
import cn.lw.study.widgets.ILayoutManager;
import cn.lw.study.widgets.MyStaggeredGridLayoutManager;

/**
 * Created by luow on 2016/6/17.
 */
public class FirstActivity extends BaseListActivity {
    private ArrayList<String> data;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_first, R.string.title, MODE_BACK);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        Intent it=new Intent(this, MainActivity.class);
        it.putExtra("appStatus", ConstantsValue.STATUS_LOGOUT);
        startActivity(it);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        super.setUpData(savedInstanceState);
        data = new ArrayList<String>();
        pullToRefreshRecycleView.setRefreshView();
    }

    @Override
    protected BaseViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_list, parent, false);
        return new SimpleAdapter(view);
    }

    @Override
    protected int getDataCount() {
        return data.size();
    }

    @Override
    protected RecyclerView.ItemDecoration geteItemDecoreton() {
        return null;
    }

    protected ILayoutManager getLayoutManager() {
        return new MyStaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void onRefresh(final int action) {
        pullToRefreshRecycleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (action == pullToRefreshRecycleView.ACTION_PULL_TO_REFRESH) {
                    data.clear();
                }
                int size = data.size();
                for (int i = size; i < size + 20; i++) {
                    data.add("luowei" + i);
                }
                if (data.size() < 100) {
                    pullToRefreshRecycleView.setLoadMoreEnable(true);
                }else{
                    pullToRefreshRecycleView.setLoadMoreEnable(false);
                }
                adapter.notifyDataSetChanged();
                pullToRefreshRecycleView.setRefreshCompleted();
            }
        },3000);

    }

    class SimpleAdapter extends BaseViewHolder {
        TextView tv;

        public SimpleAdapter(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }

        @Override
        public void onBindViewHolder(final int position) {
            tv.setText(data.get(position));
        }

        @Override
        public void onItemClick(View v, int position) {
            Toast.makeText(FirstActivity.this, "sss" + position, Toast.LENGTH_SHORT).show();
        }
    }
}