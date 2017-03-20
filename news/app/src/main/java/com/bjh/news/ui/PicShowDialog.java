package com.bjh.news.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bjh.news.R;
import com.bjh.news.image.Image;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by baijunhui on 17-3-20.
 */

public class PicShowDialog extends DialogFragment {

    @BindView(R.id.iv_pic_viewer_show)
    ImageView ivPicViewerShow;

    private String url;

    public static void show(String url, FragmentManager fragmentManager) {
        PicShowDialog dialog = new PicShowDialog();
        dialog.url = url;
        dialog.show(fragmentManager, "url");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.show_pic_dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pic_viewer_activity, container);
        ButterKnife.bind(this, root);
        Image.loadImage(url, ivPicViewerShow);
        setCancelable(true);
        return root;
    }

    @OnClick(R.id.iv_pic_viewer_show)
    public void onClick() {
        dismissAllowingStateLoss();
    }
}
