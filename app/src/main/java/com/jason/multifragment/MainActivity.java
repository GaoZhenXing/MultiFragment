package com.jason.multifragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.multifragment.struct.FunctionNoParamNoResult;
import com.jason.multifragment.struct.FunctionWithParamAndResult;
import com.jason.multifragment.struct.FunctionWithParamOnly;
import com.jason.multifragment.struct.FunctionWithResultOnly;
import com.jason.multifragment.struct.FunctionsManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private List<Fragment> fragmentList;
    private int currentIndex = 0; //标记当前fragment
    private Fragment mCurrentFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    changeTab(0);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    changeTab(1);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    changeTab(2);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initFragment();
    }

    private void initFragment() {
        fragmentList = new ArrayList<Fragment>(3);
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        changeTab(0);
    }

    private void changeTab(int index) {
        currentIndex = index;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //判断当前fragment是否为空 ,如果不为空 隐藏当前fragment
        if (null != mCurrentFragment) {
            fragmentTransaction.hide(mCurrentFragment);
        }
        //先根据tag 从 fragmenTransaction中获取之前添加的fragment,
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentList.get(index).getClass().getName());

        if (null == fragment) {
            fragment = fragmentList.get(index);
        }
        mCurrentFragment = fragment;
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.content, fragment, fragment.getClass().getName());
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }

    public void setFunctionForFragment() {
        FunctionsManager.getInstance()
                //fragment1   实现方式 无参数 无返回值
                .addFunction(new FunctionNoParamNoResult(Fragment1.INTERFACE_NPNR) {
                    @Override
                    public void function() {
                        Toast.makeText(MainActivity.this, "成功调用无返回值 无参数的接口", Toast.LENGTH_LONG).show();
                    }
                })
                //fragment1   实现  有参数  回调
                .addFunction(new FunctionWithParamOnly<Integer>(Fragment1.INTERFACE_NP) {

                    @Override
                    public void function(Integer o) {
                        Toast.makeText(MainActivity.this, "mainActivity接收到fragment1参数:" + o, Toast.LENGTH_LONG).show();
                    }
                })
                //fragment2 只有返回值 实现方式
                .addFunction(new FunctionWithResultOnly<String>(Fragment2.INTERFACE_RESULT_ONLY) {

                    @Override
                    public String function() {
                        return "Activity返回 I Love You";
                    }
                })
                //fragment2   实现  有参数 有返回值 回调
                .addFunction(new FunctionWithParamAndResult<String, Integer>(Fragment3.INTERFACE_WITH_RESULT_PARAM) {


                    @Override
                    public String function(Integer integer) {
                        return "Activity接收到参数:" + integer + ",返回 :fdaf ";
                    }
                });

    }
}
