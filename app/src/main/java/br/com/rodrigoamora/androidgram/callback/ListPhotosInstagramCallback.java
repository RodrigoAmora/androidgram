package br.com.rodrigoamora.androidgram.callback;

import br.com.rodrigoamora.androidgram.delegate.CallbackDelegate;
import br.com.rodrigoamora.androidgram.model.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPhotosInstagramCallback implements Callback<Data> {

    private CallbackDelegate<Data> delegate;
    private Data data;

    public ListPhotosInstagramCallback(CallbackDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onResponse(Call<Data> call, Response<Data> response) {
        if (response.isSuccessful()) {
            data = response.body();
            delegate.success(data);
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
