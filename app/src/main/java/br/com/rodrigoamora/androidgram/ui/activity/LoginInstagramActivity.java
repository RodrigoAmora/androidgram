package br.com.rodrigoamora.androidgram.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import br.com.rodrigoamora.androidgram.BuildConfig;
import br.com.rodrigoamora.androidgram.R;
import br.com.rodrigoamora.androidgram.dao.TokensDao;

public class LoginInstagramActivity extends AppCompatActivity {

    private static final String BASE_INSTAGRAM_API = BuildConfig.BASE_URL_INSTAGRAM_API;
    private static final String CLIENT_ID = "5bf275ee0f40441f9d3c97308b09cd9e";
    private static final String REDIRECT_URL = "http://google.com";

    private static final String AUTH_URI = BASE_INSTAGRAM_API+"oauth/authorize/"+
                                            "?client_id="+CLIENT_ID+
                                            "&redirect_uri="+REDIRECT_URL+
                                            "&response_type=token";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                        Toast.makeText(LoginInstagramActivity.this, getString(R.string.success_instagram_success), Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(LoginInstagramActivity.this, getString(R.string.error_instagram_success), Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

}
