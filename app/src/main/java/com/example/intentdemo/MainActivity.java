package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.intentdemo.databinding.ActivityMainBinding;

/**
 * @author 龚鸿飞
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.ButtonToMain.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HelloActivity.class);
            startActivity(intent);
        });

        mBinding.ButtonToBaidu.setOnClickListener(v -> {
            //设置意图
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //将字符串地址转化为网址URI地址对象，设置到意图上
            intent.setData(Uri.parse("https://www.baidu.com/"));
            startActivity(intent);
        });

        mBinding.ButtonToBing.setOnClickListener(v -> {
            //设置意图
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String s = mBinding.editKey.getText().toString();
            //将字符串地址转化为网址URI地址对象，设置到意图上
            intent.setData(Uri.parse("https://cn.bing.com/search?q=" + s));
            startActivity(intent);
        });
    }
}