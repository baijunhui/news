package com.bjh.news.presenter;

import com.bjh.news.data.NewsItem;

import java.util.List;

/**
 * Created by baijunhui on 17-3-6.
 */

public interface IMainView {

    void onQuery(String type, List<NewsItem> datas);

}
