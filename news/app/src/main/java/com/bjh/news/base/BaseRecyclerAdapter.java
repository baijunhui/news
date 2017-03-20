package com.bjh.news.base;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baijunhui on 17-3-6.
 */

public class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerHolder> {

    private List<BaseRecyclerData> items = new ArrayList<>();
    private SparseArray<Class<? extends BaseRecyclerHolder>> viewHolderMaps = new SparseArray<>();

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    public void setItems(List<? extends BaseRecyclerData> items) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.items = new ArrayList<>();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addData(List<? extends BaseRecyclerData> items) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void registerViewHolder(Class<? extends BaseRecyclerHolder> tClass, int viewType) {
        viewHolderMaps.put(viewType, tClass);
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Class<? extends BaseRecyclerHolder> holderClass = viewHolderMaps.get(viewType);
        View itemRoot = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        BaseRecyclerHolder baseRecyclerHolder = null;
        try {
            Constructor constructor = holderClass.getDeclaredConstructor(View.class);
            baseRecyclerHolder = (BaseRecyclerHolder) constructor.newInstance(itemRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseRecyclerHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position) {
        BaseRecyclerData baseRecyclerData = items.get(position);
        if (baseRecyclerData == null) {
            Log.e("bjh", "data is null");
            return;
        }
        if (getItemViewType(position) != baseRecyclerData.getViewType()) {
            Log.e("bjh", "type not equals");
            return;
        }
        holder.updateView(baseRecyclerData);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
