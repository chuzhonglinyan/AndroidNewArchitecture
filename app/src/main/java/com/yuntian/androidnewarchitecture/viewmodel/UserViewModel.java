package com.yuntian.androidnewarchitecture.viewmodel;

import android.arch.lifecycle.LiveData;

import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.androidnewarchitecture.bean.User;
import com.yuntian.androidnewarchitecture.repository.IUserData;
import com.yuntian.androidnewarchitecture.repository.UserRepository;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.lifecycle.BaseViewModle;

import java.util.List;


public class UserViewModel extends BaseViewModle implements IUserData{

    private LiveData<User> user;

    private UserRepository userRepo;


    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void init(String userId) {
        if (this.user != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        user = userRepo.getUser(userId);
    }

    public LiveData<User> getUser() {
        return this.user;
    }

    @Override
    public BaseResultLiveData<List<Repo>> getRepoList(String userId) {
        baseResultLiveData=  userRepo.getRepoList(userId);
        return baseResultLiveData;
    }


    private BaseResultLiveData<List<Repo>> baseResultLiveData;

    @Override
    public void onCleared() {
        super.onCleared();
        baseResultLiveData=null;
    }
}
