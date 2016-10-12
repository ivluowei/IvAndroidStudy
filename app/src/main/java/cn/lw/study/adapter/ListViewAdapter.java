package cn.lw.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.lw.study.R;

/**
 * Created by luow on 2016/10/9.
 */
public class ListViewAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> data;
    public ListViewAdapter(Context context, ArrayList<String> data) {
        super();
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_base_list,parent,false);
            holder.tv= (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(data.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
