package com.bjh.news.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bjh.news.R;
import com.bjh.news.base.BaseActivity;
import com.bjh.news.image.Image;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by baijunhui on 17-3-20.
 */

public class PicViewerActivity extends BaseActivity {

    private static final String VIEWER_URL = "PIC_SHOW_VIEWER";

    public static void naviFrom(Context context, String url) {
        Intent intent = new Intent(context, PicViewerActivity.class);
        intent.putExtra(VIEWER_URL, url);
        context.startActivity(intent);
    }

    @BindView(R.id.iv_pic_viewer_show)
    ImageView ivPicViewerShow;

    @Override
    public void initView() {
        String url = getIntent().getStringExtra(VIEWER_URL);
        Image.loadImage(url, ivPicViewerShow);
    }

    @Override
    public int getContentViewId() {
        return R.layout.pic_viewer_activity;
    }

    @OnClick(R.id.iv_pic_viewer_show)
    public void onClick() {
        finish();
    }
}
