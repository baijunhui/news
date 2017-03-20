package com.bjh.news;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bjh.news.base.BaseActivity;
import com.bjh.news.base.BaseRecyclerAdapter;
import com.bjh.news.data.NewsItem;
import com.bjh.news.presenter.IMainView;
import com.bjh.news.presenter.MainPresenter;
import com.bjh.news.viewHolder.NewsItemHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by baijunhui on 17-3-4.
 */

public class MainActivity extends BaseActivity implements IMainView, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_main)
    RecyclerView recyclerViewMain;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private BaseRecyclerAdapter baseRecyclerAdapter;

    private MainPresenter mainPresenter;
    private String type = Config.URI_TOP;

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        baseRecyclerAdapter = new BaseRecyclerAdapter();
        baseRecyclerAdapter.registerViewHolder(NewsItemHolder.class, R.layout.news_item);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMain.setAdapter(baseRecyclerAdapter);
        mainPresenter = MainPresenter.register(this);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.queryNewsByType(type);
            }
        });

        navView.setNavigationItemSelectedListener(this);
        mainPresenter.queryDefaultNews();
        setTitle(type);
    }

    @Override
    public int getContentViewId() {
        return R.layout.main_activity;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        handleMenuItem(item.getItemId());
        return false;
    }

    private void handleMenuItem(int itemId) {
        String type;
        switch (itemId) {
            case R.id.nav_top:
                type = Config.URI_TOP;
                break;
            case R.id.nav_caijing:
                type = Config.URI_CAIJING;
                break;
            case R.id.nav_guoji:
                type = Config.URI_GUOJI;
                break;
            case R.id.nav_guonei:
                type = Config.URI_GUONEI;
                break;
            case R.id.nav_yule:
                type = Config.URI_YULE;
                break;
            case R.id.nav_tiyu:
                type = Config.URI_TIYU;
                break;
            case R.id.nav_keji:
                type = Config.URI_KEJI;
                break;
            case R.id.nav_shehui:
                type = Config.URI_SHEHUI;
                break;
            case R.id.nav_junshi:
                type = Config.URI_JUNSHI;
                break;
            case R.id.nav_shishang:
                type = Config.URI_SHISHANG;
                break;
            default:
                type = Config.URI_TOP;
                break;
        }
        setTitle(type);
        this.type = type;
        mainPresenter.queryNewsByType(type);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void setTitle(String type) {
        toolbar.setTitle(getString(mainPresenter.getTitleByType(type)));
    }

    @Override
    public void onQuery(String type, List<NewsItem> datas) {
        baseRecyclerAdapter.setItems(datas);
        refreshLayout.setRefreshing(false);
        setTitle(type);
    }
}
