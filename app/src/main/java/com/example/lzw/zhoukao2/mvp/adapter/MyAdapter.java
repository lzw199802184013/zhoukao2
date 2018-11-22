package com.example.lzw.zhoukao2.mvp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzw.zhoukao2.R;
import com.example.lzw.zhoukao2.mvp.model.GridViewBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<GridViewBean.ListBean> list;
   private Context context;

    public MyAdapter(List<GridViewBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
//        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
                viewHolder = new ViewHolder();
                view=View.inflate(context, R.layout.layout_grid2,null);
                viewHolder.img2=view.findViewById(R.id.img2);
            viewHolder.tv_name=view.findViewById(R.id.tv_name);
            viewHolder.tv_price=view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else{

            viewHolder= (ViewHolder) view.getTag();
        }
        GridViewBean.ListBean listBean = list.get(i);
        String images = listBean.getList().get(0).getImages();
        String[] split = images.split("[|]");
        ImageLoader.getInstance().displayImage(split[0],viewHolder.img2);
        viewHolder.tv_name.setText(listBean.getList().get(0).getTitle());
        viewHolder.tv_price.setText("￥"+listBean.getList().get(0).getPrice());

        return view;
    }
    public  class  ViewHolder{

        ImageView img2;
        TextView tv_name,tv_price;
    }
}
