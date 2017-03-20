package com.bjh.news.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.bjh.news.R;
import com.bjh.news.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baijunhui on 17-3-17.
 */

public class WebActivity extends BaseActivity {

    public static final String URL = "extraUrl";
    @BindView(R.id.loading_progress_bar)
    ProgressBar loadingProgressBar;
    @BindView(R.id.web_toolbar)
    Toolbar webToolbar;

    public static void nativageFrom(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    public void initView() {
        setSupportActionBar(webToolbar);
        webToolbar.setTitle("详情");
        String url = getIntent().getStringExtra(URL);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(false);
        webView.setWebChromeClient(new CustomChromeClient());
        webView.setWebViewClient(new CustomWebClient());
        webView.loadUrl(url);
    }

    @Override
    public int getContentViewId() {
        return R.layout.web_activity;
    }

    private class CustomChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (title != null) {
                webToolbar.setTitle(title);
            }
        }
    }

    private class CustomWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            loadingProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loadingProgressBar.setVisibility(View.GONE);
        }
    }

}
