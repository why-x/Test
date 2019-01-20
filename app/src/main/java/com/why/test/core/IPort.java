package com.why.test.core;

import com.why.test.bean.MyCar;
import com.why.test.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IPort {
    /**
     * 购物车数据 加入购物车
     *
     * @return
     */
    @FormUrlEncoded
    @POST("product/getCarts")
    Observable<Result<List<MyCar>>> addmycart(@Field("uid") int uid);





}
