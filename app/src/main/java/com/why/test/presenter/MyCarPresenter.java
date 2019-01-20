package com.why.test.presenter;

import com.why.test.core.DataCall;
import com.why.test.core.IPort;
import com.why.test.core.NetWorkManager;

import io.reactivex.Observable;

public class MyCarPresenter extends BasePresenter{

    public MyCarPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IPort iPort = NetWorkManager.instance().create(IPort.class);
        return iPort.addmycart((int)args[0]);
    }
}
