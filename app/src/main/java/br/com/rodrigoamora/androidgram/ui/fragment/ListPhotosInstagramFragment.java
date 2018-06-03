package br.com.rodrigoamora.androidgram.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import br.com.rodrigoamora.androidgram.application.MyApplication;
import br.com.rodrigoamora.androidgram.callback.ListPhotosInstagramCallback;
import br.com.rodrigoamora.androidgram.component.ListPhotosInstagramComponent;
import br.com.rodrigoamora.androidgram.dao.TokensDao;
import br.com.rodrigoamora.androidgram.delegate.Delegate;
import br.com.rodrigoamora.androidgram.service.InstagramService;
import br.com.rodrigoamora.androidgram.ui.activity.LoginInstagramActivity;
import retrofit2.Call;

public class ListPhotosInstagramFragment extends Fragment implements Delegate {

    private Call<Object> call;
    private ListPhotosInstagramCallback callback;

    @Inject
    InstagramService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (TokensDao.getAccessTokenInstagram(getActivity()).equals("")) {
            Intent intent = new Intent(getActivity(), LoginInstagramActivity.class);
            startActivity(intent);
        }

        getComponents();
        callback = new ListPhotosInstagramCallback(this);
        searchPhotos();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void error() {

    }

    @Override
    public void success() {

    }

    private void getComponents() {
        MyApplication app = (MyApplication) getActivity().getApplication();
        ListPhotosInstagramComponent component = app.getListPhotosInstagramComponent();
        component.inject(this);
    }

    private void searchPhotos() {
        call = service.listPhotosSelf(TokensDao.getAccessTokenInstagram(getActivity()));
        call.enqueue(callback);
    }

}
