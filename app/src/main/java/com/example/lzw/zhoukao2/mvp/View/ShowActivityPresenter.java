package com.example.lzw.zhoukao2.mvp.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lzw.zhoukao2.R;
import com.example.lzw.zhoukao2.mvp.IView.ADeleGate;
import com.example.lzw.zhoukao2.mvp.adapter.LayoutGridAdapter;
import com.example.lzw.zhoukao2.mvp.adapter.MyAdapter;
import com.example.lzw.zhoukao2.mvp.model.GridBean;
import com.example.lzw.zhoukao2.mvp.model.GridViewBean;
import com.example.lzw.zhoukao2.mvp.model.ImageBean;
import com.example.lzw.zhoukao2.mvp.net.Helper;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.android.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class ShowActivityPresenter extends ADeleGate implements View.OnClickListener {

    private BGABanner view_vp;
    private GridView gridview;

    private List<String> mTitle = new ArrayList<>();
    private String imgUrl = "https://www.zhaoapi.cn/ad/getAd";
    private List<ImageBean.DataBean> data1 = new ArrayList<>();
    private String gridUrl = "https://www.zhaoapi.cn/product/getCatagory";
    private List<GridBean.DataBean> data2 = new ArrayList<>();
    private LayoutGridAdapter layoutGridAdapter;
    private GridView gridView;
    private String grid2Url = "https://www.zhaoapi.cn/product/getCarts?uid=71";
    private MyAdapter myAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    public void initData() {
        super.initData();

        view_vp = (BGABanner) get(R.id.view_vp);
        //轮播
        doGet();
        gridview = (GridView) get(R.id.gridview);
        //九宫格
        doGrid();
        layoutGridAdapter = new LayoutGridAdapter(context);
        gridview.setAdapter(layoutGridAdapter);

        gridView = (GridView) get(R.id.gridView);

        dogridView();
        setClick(this, R.id.img);
        mTitle.add("第一页");
        mTitle.add("第二页");
        mTitle.add("第三页");
        mTitle.add("第四页");

    }


    //轮播图
    private void doGet() {

        new Helper().get(imgUrl).result(new Helper.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                ImageBean imageBean = gson.fromJson(data, ImageBean.class);
                data1 = imageBean.getData();
                view_vp.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
//                        Picasso.with(context).load(data1.get(position).getIcon()).fit().into((ImageView) itemView);
                        ImageLoader.getInstance().displayImage(data1.get(position).getIcon(), (ImageView) itemView);
                    }
                });
                view_vp.setData(data1, mTitle);

            }

            @Override
            public void fail() {

            }
        });
    }

    //九宫格
    private void doGrid() {

        new Helper().get(gridUrl).result(new Helper.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                GridBean gridBean = gson.fromJson(data, GridBean.class);
                data2 = gridBean.getData();
                layoutGridAdapter.setList(data2);
            }

            @Override
            public void fail() {

            }
        });
    }

    //九宫格列表
    private void dogridView() {

        new Helper().get(grid2Url).result(new Helper.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                GridViewBean gridViewBean = gson.fromJson(data, GridViewBean.class);
                List<GridViewBean.ListBean> data3 = gridViewBean.getData();
                //九宫格列表
                myAdapter = new MyAdapter(data3, context);
                gridView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void fail() {

            }
        });
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img:
                PermissionUtils.permission(context, new PermissionUtils.PermissionListener() {
                    @Override
                    public void success() {
                        Intent intent = new Intent(context, CaptureActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }
}
