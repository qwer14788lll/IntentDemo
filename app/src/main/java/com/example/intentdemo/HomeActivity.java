package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
    private long exitTime = 0;

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
            UserInfo u = (UserInfo) intent.getSerializableExtra("userinfo");
            user = "账号：" + u.getUser();
            pwd = "密码：" + u.getPwd();
            isAdmin = "是否为管理员：" + u.getIsAdmin();
            intent.putExtra(EXIT_CODE_HOME, "您已退出账号");
            setResult(RESULT_OK, intent);
        }
        //将数据渲染到界面上
        mBinding.userText.setText(user);
        mBinding.pwdText.setText(pwd);
        mBinding.isAdminText.setText(isAdmin);
    }

    @Override
    public void onBackPressed() {
        //拦截系统返回键的默认逻辑
        exit();
    }

    /**
     * 自定义的退出键逻辑
     */
    private void exit() {
        long doubleTime = 1000;
        //System.currentTimeMillis()获取的是现在这个时刻距离1970年1月1日0点0分0秒的时间差（毫秒数）
        if (System.currentTimeMillis() - exitTime > doubleTime) {
            exitTime = System.currentTimeMillis();
            Toast.makeText(this, "快速点击两次退出键，可退出当前账号", Toast.LENGTH_SHORT).show();
        } else {
            //销毁当前的Activity
            finish();
            //退出整个App
            //System.exit(0);
        }
    }

    /**
     * 启动Home界面的方法
     *
     * @param activity    来源
     * @param data        要传递的数据
     * @param requestCode 请求代码
     */
    public static void startHome(Activity activity, UserInfo data, int requestCode) {
        Intent intent = new Intent(activity, HomeActivity.class);
        Bundle bundle = new Bundle();
        //需要使用Bundle对象来保存可序列化的实体类数据
        bundle.putSerializable("userinfo", data);
        intent.putExtras(bundle);
        intent.putExtra("传递方式", "返回值");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动Home界面的方法
     *
     * @param activity 来源
     * @param data     要传递的数据
     */
    public static void startHome(Activity activity, UserInfo data) {
        Intent intent = new Intent(activity, HomeActivity.class);
        Bundle bundle = new Bundle();
        //需要使用Bundle对象来保存可序列化的实体类数据
        bundle.putSerializable("userinfo", data);
        intent.putExtras(bundle);
        intent.putExtra("传递方式", "封装");
        activity.startActivity(intent);
    }
}