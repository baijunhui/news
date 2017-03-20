package com.bjh.news.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baijunhui on 17-3-19.
 */

public class NewsResult implements Serializable {

    public String reason;
    public Result<List<NewsItem>> result;
}
