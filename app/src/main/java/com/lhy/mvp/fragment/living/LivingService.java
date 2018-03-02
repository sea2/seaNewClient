package com.lhy.mvp.fragment.living;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by admin on 2017/2/14.
 */

public interface LivingService {
    @GET("room/list?pagenum=20&__version=2.1.9.1720&__plat=android&__channel=guanwang&banner=1")
    Call<LivingItems> getItems(@Query("pageno") String pageno);
}
