package br.com.rodrigoamora.androidgram.callback;

import br.com.rodrigoamora.androidgram.delegate.Delegate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPhotosInstagramCallback implements Callback<Object> {

    Delegate delegate;

    public ListPhotosInstagramCallback(Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        if (response.isSuccessful()) {
            delegate.success();
        } else {
            delegate.error();
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        delegate.error();
    }

}
