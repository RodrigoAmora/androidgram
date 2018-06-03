package br.com.rodrigoamora.androidgram.service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InstagramService {

    @GET("v1/users/self/")
    Call<Object> listPhotosSelf(@Query("access_token") String accessToken);

    @GET("v1/users/self/media/recent/")
    Call<Object> listRecentPhotos(@Query("access_token") String accessToken);

}
