package com.bjh.news.data;

import com.bjh.news.R;
import com.bjh.news.base.BaseRecyclerData;

/**
 * Created by baijunhui on 17-3-6.
 *
 *
 * {
 "uniquekey": "1eb79af416cadea606ef9712880bca13",
 "title": "中美高端峰会探讨合作模式 推动中国绿色转型",
 "date": "2017-03-17 17:02",
 "category": "国际",
 "author_name": "中国网",
 "url": "http://mini.eastday.com/mobile/170317170239824.html",
 "thumbnail_pic_s": "http://07.imgmini.eastday.com/mobile/20170317/20170317170239_ec3054c5a16a5df28aae14d2ff6a86e3_1_mwpm_03200403.jpeg",
 "thumbnail_pic_s02": "http://07.imgmini.eastday.com/mobile/20170317/20170317170239_ec3054c5a16a5df28aae14d2ff6a86e3_2_mwpm_03200403.jpeg",
 "thumbnail_pic_s03": "http://07.imgmini.eastday.com/mobile/20170317/20170317170239_ec3054c5a16a5df28aae14d2ff6a86e3_3_mwpm_03200403.jpeg"
 }
 */

public class NewsItem extends BaseRecyclerData {
    public static final int VIEW_TYPE = R.layout.news_item;

    public String uniquekey;
    public String title;
    public String date;
    public String category;
    public String author_name;
    public String url;
    public String thumbnail_pic_s;
    public String thumbnail_pic_s02;
    public String thumbnail_pic_s03;

    @Override
    public int getViewType() {
        return VIEW_TYPE;
    }
}
