package br.com.rodrigoamora.androidgram.application;

import android.app.Application;

import br.com.rodrigoamora.androidgram.component.DaggerListPhotosInstagramComponent;
import br.com.rodrigoamora.androidgram.component.ListPhotosInstagramComponent;
import br.com.rodrigoamora.androidgram.module.ListPhotosInstagramModule;
import dagger.internal.DaggerCollections;

public class MyApplication extends Application {

    ListPhotosInstagramComponent listPhotosInstagramComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        listPhotosInstagramComponent = DaggerListPhotosInstagramComponent.builder()
                                        .listPhotosInstagramModule(new ListPhotosInstagramModule(this))
                                        .build();
    }

    public ListPhotosInstagramComponent getListPhotosInstagramComponent() {
        return listPhotosInstagramComponent;
    }

}
