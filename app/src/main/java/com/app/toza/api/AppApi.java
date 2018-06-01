package com.app.toza.api;


import com.app.toza.api.responsepojos.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by chintans on 21-02-2018.
 */

public interface AppApi {

    //here base response is generic class just pass Response data type like object or arraylist

    /* when response data is object
    @POST("login.php?format=json")
    Call<BaseResponse<User>> doLogin(@Body CommonReq commonReq);*/

    /*when response data is Array
    @POST("home/searchsuggestion")
    Call<BaseResponse<List<School>>> searchSchool(@Field("terms") String schoolName);*/

    @GET("StateCity/GetAllCityStateList")
    Call<BaseResponse<List<ResponseData>>> getAllStateList();
}
