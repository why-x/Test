package com.why.test.core;

import com.why.test.bean.Result;

public interface DataCall<T> {
    void success(T data);
    void fail(Result result);
}
