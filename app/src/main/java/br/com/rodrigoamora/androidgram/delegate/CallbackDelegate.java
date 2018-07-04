package br.com.rodrigoamora.androidgram.delegate;

public interface CallbackDelegate<T> {

    void error();
    void success(T t);

}
