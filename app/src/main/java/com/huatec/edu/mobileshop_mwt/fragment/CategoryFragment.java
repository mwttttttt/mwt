package com.huatec.edu.mobileshop_mwt.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huatec.edu.mobileshop_mwt.R;
import com.huatec.edu.mobileshop_mwt.activity.GoodsListActivity;
import com.huatec.edu.mobileshop_mwt.adapter.CategoryLeftAdapter;
import com.huatec.edu.mobileshop_mwt.adapter.CategoryRightAdapter;
import com.huatec.edu.mobileshop_mwt.common.BaseFragment;
import com.huatec.edu.mobileshop_mwt.http.ProgressDialogSubscriber;
import com.huatec.edu.mobileshop_mwt.http.entity.CategoryEntity;
import com.huatec.edu.mobileshop_mwt.http.presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CategoryFragment extends BaseFragment {

    @BindView(R.id.rv_left)
    RecyclerView rv_left;
    @BindView(R.id.rv_right)
    RecyclerView rv_right;

    private List<CategoryEntity> leftData;
    private List<CategoryEntity> rightData;
    private CategoryLeftAdapter leftAdapter;
    private CategoryRightAdapter rightAdapter;


    @Override
    public int getContentViewId() {
        return R.layout.fragment_catagory;
    }


    @OnClick(R.id.ll_search)
    void search() {
        toastShort("开发中...");
    }

    @Override
    protected void initData() {
        super.initData();

        //获取一级列表
        CategoryPresenter.getTopList(new ProgressDialogSubscriber<List<CategoryEntity>>(getActivity()) {
            @Override
            public void onNext(List<CategoryEntity> categoryEntities) {
                leftData.clear();
                leftData.addAll(categoryEntities);
                leftAdapter.notifyDataSetChanged();
                leftAdapter.setSelect(0);
                loadSecondList(0);
            }
        });
    }
    private void loadSecondList(int pos) {
        if(leftData==null||leftData.size()==0){
            return;
        }
        CategoryEntity entity = leftData.get(pos);
        CategoryPresenter.getSecondList(new ProgressDialogSubscriber<List<CategoryEntity>>(getActivity()) {
            @Override
            public void onNext(List<CategoryEntity> categoryEntities) {
                rightData.clear();
                rightData.addAll(categoryEntities);
                rightAdapter.notifyDataSetChanged();
            }
        },entity.getCat_id());
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        leftData=new ArrayList<>();
        rightData=new ArrayList<>();
        LinearLayoutManager leftManager = new LinearLayoutManager(getActivity());
        leftManager.setOrientation(OrientationHelper.VERTICAL);
        rv_left.setLayoutManager(leftManager);
        GridLayoutManager rightManager = new GridLayoutManager(getActivity(),3, OrientationHelper.VERTICAL, false);
        rv_right.setLayoutManager(rightManager);
        leftAdapter = new CategoryLeftAdapter(getActivity(), leftData);
        leftAdapter.setOnItemClickListener(new CategoryLeftAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position, CategoryEntity entity) {
                leftAdapter.setSelect(position);
                loadSecondList(position);
            }

        });
        rightAdapter = new CategoryRightAdapter(getActivity(), rightData);
        rightAdapter.setOnItemClickListener(new CategoryRightAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position, CategoryEntity entity) {
                Intent intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("cat_id", entity.getCat_id());
                startActivity(intent);
            }
        });
        rv_left.setAdapter(leftAdapter);
        rv_right.setAdapter(rightAdapter);
    }
}