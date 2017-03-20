package com.bjh.news.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by baijunhui on 17-3-6.
 */

public abstract class BaseRecyclerHolder<T extends BaseRecyclerData> extends RecyclerView.ViewHolder {

    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract int getViewType();

    public abstract void updateView(T data);
}
