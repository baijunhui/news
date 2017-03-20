package com.bjh.news.presenter;

import android.util.Log;

import com.bjh.news.Config;
import com.bjh.news.R;
import com.bjh.news.api.NewsApi;
import com.bjh.news.data.NewsItem;
import com.bjh.news.data.NewsResult;
import com.bjh.news.data.Result;
import com.bjh.news.okHttp.OkHttpUtils;
import com.bjh.news.okHttp.ResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by baijunhui on 17-3-6.
 */

public class MainPresenter {

    IMainView iMainView;
    private Map<String, Integer> titleMaps = new HashMap<>();

    private MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
        titleMaps.put(Config.URI_TOP, R.string.top);
        titleMaps.put(Config.URI_CAIJING, R.string.caijing);
        titleMaps.put(Config.URI_GUOJI, R.string.guoji);
        titleMaps.put(Config.URI_GUONEI, R.string.guonei);
        titleMaps.put(Config.URI_JUNSHI, R.string.junshi);
        titleMaps.put(Config.URI_TIYU, R.string.tiyu);
        titleMaps.put(Config.URI_YULE, R.string.yule);
        titleMaps.put(Config.URI_KEJI, R.string.keji);
        titleMaps.put(Config.URI_SHISHANG, R.string.shishang);
        titleMaps.put(Config.URI_SHEHUI, R.string.shehui);
    }

    public static MainPresenter register(IMainView iMainView) {
        return new MainPresenter(iMainView);
    }

    public void queryDefaultNews() {
        queryNewsByType(Config.URI_TOP);
    }

    public int getTitleByType(String type) {
        return titleMaps.get(type);
    }


    /**
     * 另一种请求方式
     * 注意要处理订阅,记得反订阅
     *
     * @param type
     */
    public void queryNewsByType(final String type) {
        NewsApi.instance().getNews(type, Config.APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResult>() {

                    @Override
                    public void onNext(NewsResult value) {
                        if (iMainView != null && value != null && value.result != null) {
                            iMainView.onQuery(type, value.result.getData());
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    /**
     * 一种请求方式
     * @param type
     */
//    public void queryNewsByType(final String type) {
//        OkHttpUtils.getInstance().geAsync(new ResponseCallback<NewsResult>() {
//            @Override
//            public void onFail() {
//            }
//
//            @Override
//            public void onSuccess(NewsResult result) {
//                iMainView.onQuery(type, result.result.getData());
//            }
//        }, "type", type);
//    }
}
