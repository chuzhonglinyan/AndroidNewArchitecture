package com.yuntian.baselibs.base;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yuntian.baselibs.di.component.AppComponent;

public abstract class BaseActivity extends AppCompatActivity implements LifecycleOwner {

    protected Context context;


    protected AppComponent getApplicationComponent(Activity context) {
        return ((BaseApp) context.getApplication()).component();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.context = this;
        inject(getApplicationComponent(this));
        initView();
        initData(savedInstanceState);
    }


    protected abstract int getLayoutId();


    public abstract void inject(AppComponent appComponent);


    protected abstract void  initView();

    protected abstract void  initData(@Nullable Bundle savedInstanceState);


}
