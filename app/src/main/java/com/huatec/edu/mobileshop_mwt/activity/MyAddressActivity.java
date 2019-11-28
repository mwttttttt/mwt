package com.huatec.edu.mobileshop_mwt.activity;

import android.widget.TextView;

import com.huatec.edu.mobileshop_mwt.R;
import com.huatec.edu.mobileshop_mwt.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MyAddressActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    public int getContentViewId(){
        return R.layout.activity_my_address;
    }
    protected void initView(){
        super.initView();
        tvTitle.setText("我的地址");
    }
    @OnClick(R.id.iv_back)
    void close(){
        finish();
    }
}
