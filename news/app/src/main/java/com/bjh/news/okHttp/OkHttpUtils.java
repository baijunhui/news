package com.bjh.news.okHttp;

import android.os.Handler;
import android.os.Looper;

import com.bjh.news.Config;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by baijunhui on 17-3-15.
 */

public class OkHttpUtils {

    private static OkHttpUtils instance;
    private OkHttpClient okHttpClient;
    private Handler handler;
    private Gson gson;

    private OkHttpUtils() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        okHttpClient = builder.build();
        handler = new Handler(Looper.getMainLooper());
        gson = new Gson();
    }

    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    public <T> void geAsync(ResponseCallback<T> responseCallback, String... params) {
        String url = getUrl(params);
        getAsync(url, responseCallback);
    }

    private String getUrl(String... params) {
        StringBuilder builder = new StringBuilder(Config.BASE_URL);
        builder.append("?");
        if (params.length % 2 == 0 && params.length > 0) {
            for (int i = 0; i < params.length - 1; i += 2) {
                String key = params[i];
                String value = params[i + 1];
                builder.append(key).append("=").append(value).append("&");
            }
        }
        builder.append("key").append("=").append(Config.APP_KEY);

        return builder.toString();
    }


    private <T> void getAsync(String url, final ResponseCallback<T> responseCallback) {
        Request request = new Request.Builder().
                url(url).
                build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handleFail(responseCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                T result;
                if (response == null || !response.isSuccessful()) {
                    handleFail(responseCallback);
                    return;
                }
                String responseString = response.body().string();
                if (String.class.equals(responseCallback.mClass)) {
                    result = (T) responseString;
                } else {
                    result = gson.fromJson(responseString, responseCallback.mClass);
                }
                handleSuccess(responseCallback, result);
            }
        });
    }

    private <T> void handleSuccess(final ResponseCallback<T> responseCallback, final T result) {
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    responseCallback.onSuccess(result);
                }
            });
        }
    }

    private <T> void handleFail(final ResponseCallback<T> responseCallback) {
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    responseCallback.onFail();
                }
            });
        }
    }
}
