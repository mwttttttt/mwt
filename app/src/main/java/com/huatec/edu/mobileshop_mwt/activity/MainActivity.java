package com.huatec.edu.mobileshop_mwt.activity;

import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huatec.edu.mobileshop_mwt.R;
import com.huatec.edu.mobileshop_mwt.common.BaseActivity;
import com.huatec.edu.mobileshop_mwt.fragment.NavigationFragment;

public class MainActivity extends BaseActivity {
    public @LayoutRes
    int getContentViewId(){
        return R.layout.activity_main;
    }
    protected void initView(){
        super.initView();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.f1_main,new NavigationFragment());
        //transaction.replace(R.id.bt_login,new PersonalFragment());
        transaction.commit();
    }
}
