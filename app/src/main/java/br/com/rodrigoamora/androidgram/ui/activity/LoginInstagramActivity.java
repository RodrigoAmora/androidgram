package br.com.rodrigoamora.androidgram.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import br.com.rodrigoamora.androidgram.BuildConfig;
import br.com.rodrigoamora.androidgram.dao.TokensDao;

public class LoginInstagramActivity extends Activity {

    private static final String BASE_INSTAGRAM_API = BuildConfig.BASE_URL_INSTAGRAM_API;
    private static final String CLIENT_ID = "5bf275ee0f40441f9d3c97308b09cd9e";
    private static final String REDIRECT_URL = "http://google.com";

    private static final String AUTH_URI = BASE_INSTAGRAM_API+"/oauth/authorize/"+
                                            "?client_id="+CLIENT_ID+
                                            "&redirect_uri="+REDIRECT_URL+
                                            "&response_type=code";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        WebView wb = new WebView(this);
        setContentView(wb);
        setClient(this, wb);
        wb.loadUrl(AUTH_URI);
    }

    private void setClient(final Activity act, WebView webView) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(REDIRECT_URL)) {
                    if (url.contains("access_token")) {
                        String accessToken = url.split("#access_token=")[1];
                        TokensDao.saveAccessTokenInstagram(LoginInstagramActivity.this, accessToken);
                        finish();
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

}
