package com.example.intentdemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intentdemo.databinding.ActivityHomeBinding;

/**
 * @author Surface Pro 6
 */
public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding mBinding;
    private String user;
    private String pwd;
    private String isAdmin;
    private static final String METHOD_1 = "普通";
    private static final String METHOD_2 = "封装";
    private static final String METHOD_3 = "返回值";
    public static final String EXIT_CODE_HOME = "exit_code_home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        //获取传过来的Intent
        Intent intent = getIntent();
        String method = intent.getStringExtra("传递方式");
        if (METHOD_1.equals(method)) {
            //获取传过来的数据
            user = "账号：" + intent.getStringExtra("user");
            pwd = "密码：" + intent.getStringExtra("pwd");
            isAdmin = "是否为管理员：" + intent.getStringExtra("isAdmin");
        } else if (METHOD_2.equals(method)) {
            //获取传过来的封装数据
            UserInfo u = (UserInfo) intent.getSerializableExtra("userinfo");
            user = "账号：" + u.getUser();
            pwd = "密码：" + u.getPwd();
            isAdmin = "是否为管理员：" + u.getIsAdmin();
        } else if (METHOD_3.equals(method)) {
            intent.putExtra(EXIT_CODE_HOME, "您已退出账号");
            setResult(RESULT_OK, intent);
        }
        //将数据渲染到界面上
        mBinding.userText.setText(user);
        mBinding.pwdText.setText(pwd);
        mBinding.isAdminText.setText(isAdmin);
    }
}