package com.example.lzw.zhoukao2.mvp.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.lzw.zhoukao2.R;
import com.example.lzw.zhoukao2.mvp.IView.ADeleGate;

public class RegisterActivityPresenter extends ADeleGate implements View.OnClickListener {
    private TextView et_name1;
    private TextView et_password1;
    private TextView et_password2;
    private SharedPreferences sp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {
        super.initData();
        et_name1=(TextView)get(R.id.et_name1);
        et_password1=(TextView)get(R.id.et_password1);
        et_password2=(TextView)get(R.id.et_password2);
        setClick(this,R.id.register1);
    }

    private  Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register1:
                    doRegister1();
                break;

        }
    }

    private void doRegister1() {
        String name=et_name1.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            toast(context,"请输入账号名字");
            return;
        }
        String password = et_password1.getText().toString().trim();
        if (TextUtils.isEmpty(password)){
            toast(context,"请输入密码");
            return;
        }
        String password2=et_password2.getText().toString().trim();
        if (!password2.equals(password)){
            toast(context,"请保持密码一致");
            return;
        }
        sp=context.getSharedPreferences("user",context.MODE_PRIVATE);
        sp.edit().putString("name",name).putString("password",password).putString("password2",password2).commit();
        context.startActivity(new Intent(context,MainActivity.class));
        ((RegisterActivity)context).finish();

    }
}
