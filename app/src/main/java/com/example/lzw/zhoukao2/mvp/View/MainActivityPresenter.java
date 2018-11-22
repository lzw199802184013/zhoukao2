package com.example.lzw.zhoukao2.mvp.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lzw.zhoukao2.R;
import com.example.lzw.zhoukao2.mvp.IView.ADeleGate;

public class MainActivityPresenter extends ADeleGate implements View.OnClickListener {
    private TextView et_name;
    private TextView et_password;
    private CheckBox remeber;
    private SharedPreferences sp;
    private String name1 = "";
    private String password1 = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        //获取控件id
        et_name = (TextView) get(R.id.et_name);
        et_password = (TextView) get(R.id.et_password);
        sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        remeber = (CheckBox) get(R.id.remeber);
        boolean b1 = sp.getBoolean("islogin", false);
        if (b1) {
            name1 = sp.getString("name", "");
            password1 = sp.getString("password", "");
            et_name.setText(name1);
            et_password.setText(password1);
            remeber.setChecked(true);
        } else {
            et_name.setText("");
            et_password.setText("");
            remeber.setChecked(false);
        }
        setClick(this, R.id.login, R.id.register);
        //check点击事件
        remeber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sp.edit().putBoolean("islogin", b).commit();
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
            case R.id.login:
                //登录
                doLogin();

                break;

            case R.id.register:
                //跳转到注册页面
                doRegister();
                break;


        }
    }

    private void doLogin() {
        String username = et_name.getText().toString().trim();
        String userpass = et_password.getText().toString().trim();
        name1 = sp.getString("name", "");
        password1 = sp.getString("password", "");
        if (TextUtils.isEmpty(username)) {
            toast(context, "请输入账号名字");
            return;
        }
        if (TextUtils.isEmpty(userpass)) {
            toast(context, "请输入密码");
            return;
        }

        if (name1.equals(username) && password1.equals(userpass)) {
            toast(context, "登录成功");
            context.startActivity(new Intent(context, ShowActivity.class));

        } else {
            toast(context, "请输入正确的账号密码");
        }

    }

    private void doRegister() {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }
}
