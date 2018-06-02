package br.com.rodrigoamora.androidgram.dao;

import android.content.Context;
import android.content.SharedPreferences;

public class TokensDao {

    private static String ACCESS_TOKEN_INSTAGRAM = "access_token_instagram";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("androidgram", Context.MODE_PRIVATE);
    }

    public static void saveAccessTokenInstagram(Context context, String accessToken) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_TOKEN_INSTAGRAM, accessToken);
        editor.commit();
    }

    public static String getAccessTokenInstagram(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(ACCESS_TOKEN_INSTAGRAM, "");
    }

}
