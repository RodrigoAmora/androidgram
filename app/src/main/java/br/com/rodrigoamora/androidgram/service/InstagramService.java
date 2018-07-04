package br.com.rodrigoamora.androidgram.service;

import br.com.rodrigoamora.androidgram.model.Data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InstagramService {

    @GET("v1/users/self/")
    Call<Object> getProfile(@Query("access_token") String accessToken);

    @GET("v1/users/self/media/recent/")
    Call<Data> listRecentPhotos(@Query("access_token") String accessToken, @Query("count") int count);

    @GET("oauth/")
    Call<Object> oatuh(@Query("access_token") String accessToken);

}
