package com.example.intentdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.intentdemo.databinding.ActivityMainBinding;

/**
 * @author 龚鸿飞
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    public static int CALL_CODE = 1;

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

        mBinding.ButtonToPhone.setOnClickListener(v -> {
            //拨打电话的权限
            String callPermission = Manifest.permission.CALL_PHONE;
            //检查是否有拨打电话的权限,如果为true（申请权限），否则（直接拨打）
            //已授权（常量为0），未授权（常量为-1）
            if (ActivityCompat.checkSelfPermission(this, callPermission) != PackageManager.PERMISSION_GRANTED) {
                //请求授权（要申请权限的Activity，要申请的权限数组，请求代码）
                ActivityCompat.requestPermissions(this, new String[]{callPermission}, CALL_CODE);
            } else {
                call(Intent.ACTION_CALL);
            }
        });

        mBinding.ButtonToSMS.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            //指定要收短信的电话号码
            intent.setData(Uri.parse("smsto:17520439994"));
            //设定短信的发送内容（我给他预设的）sms_body表达的就是短信的主体内容
            intent.putExtra("sms_body","年轻人发出的第一条短信");
            startActivity(intent);
        });

        mBinding.ButtonToCamera.setOnClickListener(v -> {
            //启动系统相机
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        });
    }

    /**
     * 打电话
     * @param action Intent.ACTION_CALL直接拨打电话 或 Intent.ACTION_DIAL将电话号码带给电话应用
     */
    private void call(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setData(Uri.parse("tel:17520439994"));
        startActivity(intent);
    }

    /**
     * 请求授权的回调方法，在用户作出选择后发生
     *
     * @param requestCode  请求代码
     * @param permissions  权限数组
     * @param grantResults 授权结果数组
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //判断请求来源
        if (requestCode == CALL_CODE) {
            //判断是否有授权结果，并且授权结果是否为已授权
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call(Intent.ACTION_CALL);
            } else {
                Toast.makeText(this, "未授予拨打电话的权限，请手动拨打", Toast.LENGTH_SHORT).show();
                //Intent.ACTION_DIAL将电话号码带给电话应用
                call(Intent.ACTION_DIAL);
            }
        }
    }
}