package cn.lw.study.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import cn.lw.study.R;

/**
 * Created by lw on 2016/6/22.
 */
public class ViewPagerFragment extends Fragment {
    private boolean isVisibleToUser;
    private boolean isCreatedView;
    private TextView tv;
    private int param=-1;
    private boolean isLoadData;
    private ProgressBar progressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("ViewPagerFragment","onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ViewPagerFragment","onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("ViewPagerFragment","onCreateView()");
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isCreatedView = true;
        Log.e("ViewPagerFragment","onViewCreated()");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("ViewPagerFragment",isVisibleToUser+"");
        this.isVisibleToUser = isVisibleToUser;
            loadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("ViewPagerFragment","onActivityCreated()");
        tv = (TextView) getView().findViewById(R.id.textView3);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar2);
        if(param != -1){
            Log.e("ViewPagerFragment","操你吗是是是");
            setUpData();
        }
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ViewPagerFragment","onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("ViewPagerFragment","onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isCreatedView = false;
        Log.e("ViewPagerFragment","onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("ViewPagerFragment","onDetach()");
    }

    private void loadData() {
        if (isVisibleToUser && isCreatedView && !isLoadData) {
            isLoadData=true;
            Log.e("ViewPagerFragment","走进了");
            Random random=new Random();
            param=random.nextInt(10);
            setUpData();
        }
    }

    private void setUpData() {
        Log.e("ViewPagerFragment","走进了22");
        progressBar.setVisibility(View.GONE);
        tv.setText("load Data:"+param);
    }
}