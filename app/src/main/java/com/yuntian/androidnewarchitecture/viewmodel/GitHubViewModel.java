package com.yuntian.androidnewarchitecture.viewmodel;

import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.androidnewarchitecture.repository.GitHubRepository;
import com.yuntian.androidnewarchitecture.repository.IGitHubData;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.lifecycle.BaseViewModle;

import java.util.List;


public class GitHubViewModel extends BaseViewModle<GitHubRepository> implements IGitHubData {




    public void init(String userId) {

    }

    @Override
    public BaseResultLiveData<List<Repo>> getRepoList(String userId) {
        return repo.getRepoList(userId);
    }

    @Override
    public BaseResultLiveData<List<User>> getUserList() {
        return repo.getUserList();
    }

    @Override
    public void onCleared() {
        super.onCleared();
        repo.getRequestManager().cancelCalls();
    }
}