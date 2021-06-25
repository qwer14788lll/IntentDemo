package com.example.intentdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.intentdemo.databinding.ActivityLoginBinding;

import java.util.Objects;

/**
 * @author Surface Pro 6
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;
    private String user;
    private String pwd;
    private String isAdmin;
    private static final int REQUEST_CODE_LOGIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.ButtonLogin1.setOnClickListener(v -> {
            if (init()) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("pwd", pwd);
                intent.putExtra("isAdmin", isAdmin);
                intent.putExtra("传递方式", "普通");
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
                intent.putExtra("传递方式", "封装");
                startActivity(intent);
            }
        });

        mBinding.ButtonLogin3.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("传递方式", "返回值");
            //具有返回值的跳转，第二个参数是请求代码
            startActivityForResult(intent, REQUEST_CODE_LOGIN);
        });

        mBinding.ButtonLogin4.setOnClickListener(v -> {
            if (init()) {
                UserInfo u = new UserInfo(user, pwd, isAdmin);
                //HomeActivity.startHome(LoginActivity.this, u);
                HomeActivity.startHome(LoginActivity.this, u,REQUEST_CODE_LOGIN);
            }
        });
    }

    /**
     * 处理返回数据的回调方法
     *
     * @param requestCode 请求代码
     * @param resultCode  返回代码
     * @param data        返回的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //检查请求代码是否一致
        if (requestCode == REQUEST_CODE_LOGIN) {
            if (resultCode == RESULT_OK) {
                String result = Objects.requireNonNull(data).getStringExtra(HomeActivity.EXIT_CODE_HOME);
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
            }
        }
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