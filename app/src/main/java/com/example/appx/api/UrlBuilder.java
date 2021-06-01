package com.example.appx.api;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlBuilder {
    private static final String API_BASIC = "https://api.todoist.com/rest/v1";
    public static final String API_DATA = "/tasks";
    public static final String API_TOKEN = "token";

    public static URL generateURL(String apiDataType) {
        Uri builtUri = Uri.parse(API_BASIC + apiDataType)
                .buildUpon()
                .appendQueryParameter(API_TOKEN, "f23900579ea488c624435396c05d250a86c40d8b")
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
