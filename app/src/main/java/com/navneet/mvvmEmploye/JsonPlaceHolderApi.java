package com.navneet.mvvmEmploye;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("61cf7d91-a7f8-405e-b505-67926b759d78")
    Call<JsonObject> getStatus();

}
