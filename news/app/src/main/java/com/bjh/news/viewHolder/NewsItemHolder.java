package com.bjh.news.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjh.news.MainActivity;
import com.bjh.news.R;
import com.bjh.news.base.BaseRecyclerHolder;
import com.bjh.news.data.NewsItem;
import com.bjh.news.image.Image;
import com.bjh.news.ui.PicShowDialog;
import com.bjh.news.ui.PicViewerActivity;
import com.bjh.news.ui.WebActivity;
import com.bjh.news.widget.SquareImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by baijunhui on 17-3-6.
 */

public class NewsItemHolder extends BaseRecyclerHolder<NewsItem> {

    @BindView(R.id.tv_news_title)
    TextView tvNewsTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.square_image)
    SquareImageView squareImage;
    @BindView(R.id.square_image_1)
    SquareImageView squareImage1;
    @BindView(R.id.square_image_2)
    SquareImageView squareImage2;

    private NewsItem newsItem;

    public NewsItemHolder(View itemView) {
        super(itemView);
    }

    @Override
    public int getViewType() {
        return R.layout.news_item;
    }

    @Override
    public void updateView(NewsItem data) {
        if (data == null) {
            return;
        }
        this.newsItem = data;
        tvNewsTitle.setText(data.title);
        tvDate.setText(data.date);
        tvAuthor.setText(data.author_name);
        loadImage(data.thumbnail_pic_s, squareImage);
        loadImage(data.thumbnail_pic_s02, squareImage1);
        loadImage(data.thumbnail_pic_s03, squareImage2);
    }

    private void loadImage(String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            imageView.setVisibility(View.VISIBLE);
            Image.loadImage(url, imageView);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick({R.id.square_image, R.id.square_image_1, R.id.square_image_2, R.id.rl_item_root})
    public void onClick(View view) {
        if (newsItem == null) {
            return;
        }
        MainActivity mainActivity = null;
        if (view.getContext() instanceof MainActivity) {
            mainActivity = (MainActivity) view.getContext();
        }
        if (mainActivity == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.square_image:
                PicShowDialog.show(newsItem.thumbnail_pic_s, mainActivity.getSupportFragmentManager());
                break;
            case R.id.square_image_1:
                PicShowDialog.show(newsItem.thumbnail_pic_s02, mainActivity.getSupportFragmentManager());
                break;
            case R.id.square_image_2:
                PicShowDialog.show(newsItem.thumbnail_pic_s03, mainActivity.getSupportFragmentManager());
                break;
            case R.id.rl_item_root:
                WebActivity.nativageFrom(tvDate.getContext(), newsItem.url);
                break;
        }
    }
}
