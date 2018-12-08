package com.yuntian.androidnewarchitecture.repository;

import com.yuntian.androidnewarchitecture.bean.GankInfo;
import com.yuntian.androidnewarchitecture.net.service.GankService;
import com.yuntian.androidnewarchitecture.contract.GankContract;
import com.yuntian.baselibs.data.BaseResult;
import com.yuntian.baselibs.lifecycle.BaseRepository;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.rxjava.CustomSubscriber;
import com.yuntian.baselibs.rxjava.RxHandleResult;

import java.util.List;

import io.reactivex.Flowable;

public class GankRepository extends BaseRepository<GankService> implements GankContract {

    private static final String TAG = "GankRepository";

    public GankRepository(GankService service) {
        super(service);
    }


    @Override
    public BaseResultLiveData<List<GankInfo>> getGankInfoList(String dataType, int page) {
        final BaseResultLiveData<List<GankInfo>> data = BaseResultLiveData.newIntance();
        Flowable<List<GankInfo>> flowable = service.getGankInfoList(dataType, page).compose(RxHandleResult.handleFlowableResult());
        flowable.subscribe(new CustomSubscriber<List<GankInfo>>(getRxManager()) {
            @Override
            public void onResponse(BaseResult<List<GankInfo>> baseResult) {
                data.setValue(baseResult);
            }
        });
        return data;
    }


}