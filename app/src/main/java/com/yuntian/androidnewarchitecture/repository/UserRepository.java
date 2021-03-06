package com.yuntian.androidnewarchitecture.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.yuntian.androidnewarchitecture.base.data.BaseResult;
import com.yuntian.androidnewarchitecture.base.data.BaseResultLiveData;
import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.androidnewarchitecture.bean.User;
import com.yuntian.androidnewarchitecture.net.NetCallback;
import com.yuntian.androidnewarchitecture.net.Webservice;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository implements IUserData {

    private static final String TAG = "UserRepository";

    private Webservice webservice;

    public UserRepository(Webservice webservice) {
        this.webservice = webservice;
    }

    // ...
    public LiveData<User> getUser(String userId) {
        // This is not an optimal implementation, we'll fix it below
        final MutableLiveData<User> data = new MutableLiveData<>();
        webservice.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                // error case is left out for brevity
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

            }
        });
        return data;
    }

    @Override
    public BaseResultLiveData<List<Repo>> getRepoList(String userId) {
        // This is not an optimal implementation, we'll fix it below
        final BaseResultLiveData<List<Repo>> data = BaseResultLiveData.newIntance();
        webservice.listRepos(userId).enqueue(new NetCallback<List<Repo>>() {
            @Override
            public void onResponse(BaseResult<List<Repo>> baseResult) {
                Log.d(TAG, "回调一次" + Thread.currentThread().getName());
                data.setValue(baseResult);
            }
        });
        return data;
    }
}
