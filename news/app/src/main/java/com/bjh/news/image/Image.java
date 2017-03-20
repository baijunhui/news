package com.bjh.news.image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by baijunhui on 17-3-17.
 */

public class Image {

    public static void loadImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext()).
                load(url).into(imageView);
    }
}
