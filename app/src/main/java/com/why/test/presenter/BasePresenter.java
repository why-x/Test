package com.why.test.presenter;

import com.why.test.bean.Result;
import com.why.test.core.DataCall;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter {
    DataCall dataCall;


    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    protected abstract Observable observable(Object... args);

    public void request(Object... args) {
        observable(args).compose(new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread());
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        dataCall.success(result);
                    }
                }, new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        dataCall.fail(result);
                    }
                });


    }

    public void unbind() {
        dataCall = null;
    }
}
