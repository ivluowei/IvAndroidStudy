package cn.lw.study.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by luow on 2016/9/27.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder{
    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, getAdapterPosition());
            }
        });
    }

    public abstract void onBindViewHolder(int position);
    public abstract void onItemClick(View v,int position);

}
