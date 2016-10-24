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
 * Created by lw on 2016/6/21.
 */
public class SettingFragment extends Fragment{
    private boolean isVisibleToUser;
    private boolean isCreatedView;
    private TextView tv;
    private int param=-1;
    private boolean isLoadData;
    private ProgressBar progressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("SettingFragment","onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("SettingFragment","onCreate()");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("SettingFragment","onCreateView()");
        return inflater.inflate(R.layout.setting_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isCreatedView = true;
        Log.e("SettingFragment","onViewCreated()");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("SettingFragment",isVisibleToUser+"");
        this.isVisibleToUser = isVisibleToUser;
        loadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("SettingFragment","onActivityCreated()");
        tv = (TextView) getView().findViewById(R.id.textView2);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
        if(param!=-1){
            Log.e("SettingFragment","操你吗");
            setUpData();
        }
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("SettingFragment","onPause()");
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
        Log.e("SettingFragment","onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("SettingFragment","onDetach()");
    }

    private void loadData() {
        if (isVisibleToUser && isCreatedView && !isLoadData) {
            Log.e("SettingFragment","走进了");
            isLoadData=true;
            Random random=new Random();
            param=random.nextInt(10);
            setUpData();
        }
    }

    private void setUpData() {
        progressBar.setVisibility(View.GONE);
        Log.e("SettingFragment","走进了22");
        tv.setText("load Data:"+param);
    }
}
