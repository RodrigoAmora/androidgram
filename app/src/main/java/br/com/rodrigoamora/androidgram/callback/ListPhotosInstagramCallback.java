package br.com.rodrigoamora.androidgram.callback;

import br.com.rodrigoamora.androidgram.delegate.Delegate;
import br.com.rodrigoamora.androidgram.model.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPhotosInstagramCallback implements Callback<Data> {

    private Delegate delegate;
    private Data data;

    public ListPhotosInstagramCallback(Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onResponse(Call<Data> call, Response<Data> response) {
        if (response.isSuccessful()) {
            data = response.body();
            delegate.success();
        } else {
            delegate.error();
        }
    }

    @Override
    public void onFailure(Call<Data> call, Throwable t) {
        delegate.error();
    }

    public Data getData() {
        return data;
    }

}
