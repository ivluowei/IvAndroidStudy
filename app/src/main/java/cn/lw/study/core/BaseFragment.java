package cn.lw.study.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luow on 2016/10/10.
 */
public abstract class BaseFragment extends Fragment{

    protected abstract int getLayooutId();
    protected abstract void setUpView();

    /**
     * 最先调用
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * 在onCreateView()之前调用，适合初始化数据
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 布局和控件初始化
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayooutId(),container,false);
    }

    /**
     * 在onCreateView()之后调用
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpView();
    }

}
