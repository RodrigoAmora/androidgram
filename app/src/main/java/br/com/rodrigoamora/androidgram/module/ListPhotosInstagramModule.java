package br.com.rodrigoamora.androidgram.module;

import br.com.rodrigoamora.androidgram.BuildConfig;
import br.com.rodrigoamora.androidgram.application.MyApplication;
import br.com.rodrigoamora.androidgram.service.InstagramService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ListPhotosInstagramModule {

    private MyApplication application;

    public ListPhotosInstagramModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    public InstagramService getListOptionsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_INSTAGRAM_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InstagramService service = retrofit.create(InstagramService.class);
        return service;
    }

}
