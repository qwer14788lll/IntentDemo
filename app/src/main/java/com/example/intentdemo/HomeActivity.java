package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.intentdemo.databinding.ActivityHomeBinding;

/**
 * @author Surface Pro 6
 */
public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        //获取传过来的Intent
        Intent intent = getIntent();
        //获取传过来的数据
        String user = "账号：" + intent.getStringExtra("user");
        String pwd = "密码：" + intent.getStringExtra("pwd");
        String isAdmin = "是否为管理员：" + intent.getStringExtra("isAdmin");
        //将数据渲染到界面上
        mBinding.userText.setText(user);
        mBinding.pwdText.setText(pwd);
        mBinding.isAdminText.setText(isAdmin);
    }
}