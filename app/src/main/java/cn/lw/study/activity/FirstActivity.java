package cn.lw.study.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.lw.study.R;
import cn.lw.study.core.BaseListActivity;
import cn.lw.study.core.BaseViewHolder;

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
    public void onRefresh(int action) {
        data.clear();
        for (int i = 0; i < 20; i++) {
            data.add("luowei" + i);
        }
        adapter.notifyDataSetChanged();
        pullToRefreshRecycleView.setStopRefreshView();
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
            Toast.makeText(FirstActivity.this,"sss"+position, Toast.LENGTH_SHORT).show();
        }
    }
}