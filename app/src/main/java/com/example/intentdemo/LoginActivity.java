package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.intentdemo.databinding.ActivityLoginBinding;

/**
 * @author Surface Pro 6
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;
    private String user;
    private String pwd;
    private String isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        //普通的传值方法
        mBinding.ButtonLogin1.setOnClickListener(v -> {
            if (init()) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("pwd", pwd);
                intent.putExtra("isAdmin", isAdmin);
                intent.putExtra("传递方式","普通");
                startActivity(intent);
            }
        });

        mBinding.ButtonLogin2.setOnClickListener(v -> {
            if (init()) {
                UserInfo u = new UserInfo(user, pwd, isAdmin);
                Bundle bundle = new Bundle();
                //需要使用Bundle对象来保存可序列化的实体类数据
                bundle.putSerializable("userinfo", u);
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("传递方式","封装");
                startActivity(intent);
            }
        });
    }

    /**
     * 判断用户是否已经有输入
     *
     * @return true为有，反之没有
     */
    private boolean init() {
        user = mBinding.user.getText().toString();
        pwd = mBinding.pwd.getText().toString();
        isAdmin = mBinding.isAdmin.isChecked() ? "是" : "否";
        if ("".equals(user) || "".equals(pwd)) {
            Toast.makeText(LoginActivity.this, "请填写账号和密码", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
}