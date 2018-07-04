package br.com.rodrigoamora.androidgram.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import br.com.rodrigoamora.androidgram.R;
import br.com.rodrigoamora.androidgram.application.MyApplication;
import br.com.rodrigoamora.androidgram.callback.ListPhotosInstagramCallback;
import br.com.rodrigoamora.androidgram.component.ListPhotosInstagramComponent;
import br.com.rodrigoamora.androidgram.dao.TokensDao;
import br.com.rodrigoamora.androidgram.delegate.Delegate;
import br.com.rodrigoamora.androidgram.model.Data;
import br.com.rodrigoamora.androidgram.model.Photo;
import br.com.rodrigoamora.androidgram.service.InstagramService;
import br.com.rodrigoamora.androidgram.ui.activity.LoginInstagramActivity;
import butterknife.BindView;
import retrofit2.Call;

public class ListPhotosInstagramFragment extends Fragment implements com.google.android.gms.maps.OnMapReadyCallback, Delegate {

    MapFragment mapFragment;

    private Call<Data> call;
    private Data data;
    private ListPhotosInstagramCallback callback;

    @Inject
    InstagramService service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_photos_instagram_fragment, container, false);

        if (!verifyAccessToken()) {
            Intent intent = new Intent(getActivity(), LoginInstagramActivity.class);
            startActivity(intent);
        } else {
            getComponents();
            mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            callback = new ListPhotosInstagramCallback(this);
            searchPhotos();
        }

        return rootView;
    }

    @Override
    public void error() {

    }

    @Override
    public void success() {
        data = callback.getData();
        mapFragment.getMapAsync(this);
    }

    private void getComponents() {
        MyApplication app = (MyApplication) getActivity().getApplication();
        ListPhotosInstagramComponent component = app.getListPhotosInstagramComponent();
        component.inject(this);
    }

    private void searchPhotos() {
        String accessToken = TokensDao.getAccessTokenInstagram(getActivity());
        int count = 200;
        call = service.listRecentPhotos(accessToken, count);
        call.enqueue(callback);
    }

    private boolean verifyAccessToken() {
        if (TokensDao.getAccessTokenInstagram(getActivity()).equals("")) {
            return false;
        }
        return true;
    }

    @SuppressLint("MissingPermission")
    @Override

    public void onMapReady(GoogleMap map) {
        if (data != null && !data.getData().isEmpty()) {
            for (Photo photo : data.getData()) {
                String title = "";
                if (photo.getCaption() != null && !photo.getCaption().getText().isEmpty()) {
                    title = photo.getCaption().getText();
                } else {
                    title = "aa";
                }

                LatLng latLng = new LatLng(photo.getLocation().getLatitude(), photo.getLocation().getLongitude());
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 20);
                map.moveCamera(update);
                map.addMarker(new MarkerOptions()
                        .title(title)
                        //.snippet(getArguments().getString("desc"))
                        .position(latLng));
                map.setMyLocationEnabled(false);
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }

            int size = data.getData().size();
            LatLng latLng = new LatLng(data.getData().get(size - 1).getLocation().getLatitude(), data.getData().get(size - 1).getLocation().getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 20);
            map.moveCamera(update);
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

}
