package com.example.lzw.zhoukao2.mvp.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.lzw.zhoukao2.R;
import com.example.lzw.zhoukao2.mvp.model.GridBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

public class LayoutGridAdapter extends BaseAdapter {

    private List<GridBean.DataBean> objects = new ArrayList<GridBean.DataBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public LayoutGridAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.layout_grid, null);
            viewHolder.image = convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        GridBean.DataBean dataBean = objects.get(position);
//        Picasso.with(context).load(dataBean.getIcon()).fit().into(viewHolder.image);
        ImageLoader.getInstance().displayImage(dataBean.getIcon(),viewHolder.image);

        return convertView;
    }

    public void setList(List<GridBean.DataBean> list) {
        this.objects = list;
        notifyDataSetChanged();
    }


    protected class ViewHolder {
        ImageView image;

    }
}
