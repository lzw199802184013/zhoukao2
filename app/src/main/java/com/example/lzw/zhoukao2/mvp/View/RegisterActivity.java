package com.example.lzw.zhoukao2.mvp.View;

import com.example.lzw.zhoukao2.mvp.presenter.ActivityPresenter;

public class RegisterActivity extends ActivityPresenter<RegisterActivityPresenter> {
    @Override
    public Class<RegisterActivityPresenter> getClassDeleGate() {
        return RegisterActivityPresenter.class;
    }
}
