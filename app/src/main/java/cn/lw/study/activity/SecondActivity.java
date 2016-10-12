package cn.lw.study.activity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import cn.lw.study.R;
import cn.lw.study.adapter.ListViewAdapter;
import cn.lw.study.core.BaseActivity;

/**
 * Created by luow on 2016/10/9.
 */
public class SecondActivity extends BaseActivity {
    private ListView lv;
    private ListViewAdapter adapter;
    private ArrayList<String> data;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_second, R.string.title, -1, MODE_BACK);
    }

    @Override
    protected void setUpView() {
        data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("5");
        data.add("5");
        data.add("5");
        data.add("5");
        lv = (ListView) findViewById(R.id.lv);
        adapter = new ListViewAdapter(this, data);
        lv.setAdapter(adapter);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }
}
