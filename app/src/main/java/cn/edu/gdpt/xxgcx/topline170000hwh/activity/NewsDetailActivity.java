package cn.edu.gdpt.xxgcx.topline170000hwh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.edu.gdpt.xxgcx.topline170000hwh.R;
import cn.edu.gdpt.xxgcx.topline170000hwh.bean.NewsBean;

public class NewsDetailActivity extends AppCompatActivity {

    private WebView webView;
    private NewsBean bean;
    private String newsUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        //将存储在intent内容的附加数据读出
        bean = (NewsBean) getIntent().getSerializableExtra("newsBean");
        if(bean==null){
            return;//如果没有数据，则退出
        }
        newsUrl=bean.getNewsUrl();
        initWebView();
    }
    private void initWebView() {
        webView.loadUrl(newsUrl);
        WebSettings mWebSetting = webView.getSettings();
        mWebSetting.setSupportMultipleWindows(true);
        mWebSetting.setLoadWithOverviewMode(true);
        mWebSetting.setUseWideViewPort(true);
        mWebSetting.setDefaultTextEncodingName("GBK");
        mWebSetting.setLoadsImagesAutomatically(true);
        mWebSetting.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;}
        });
    }
    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
    }
}
