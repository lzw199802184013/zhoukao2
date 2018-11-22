package com.example.lzw.zhoukao2.mvp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lzw.zhoukao2.R;
import com.example.lzw.zhoukao2.mvp.presenter.ActivityPresenter;

public class MainActivity extends ActivityPresenter<MainActivityPresenter> {


    @Override
    public Class<MainActivityPresenter> getClassDeleGate() {
        return MainActivityPresenter.class;
    }
}
