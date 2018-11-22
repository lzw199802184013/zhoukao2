package com.example.lzw.zhoukao2.mvp.View;

import com.example.lzw.zhoukao2.mvp.presenter.ActivityPresenter;

public class ShowActivity extends ActivityPresenter<ShowActivityPresenter> {
    @Override
    public Class<ShowActivityPresenter> getClassDeleGate() {
        return ShowActivityPresenter.class;
    }
}
