package com.example.q_thjen.jsoupdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewCourse extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_course);

        webView = findViewById(R.id.webView);

        int id = getIntent().getExtras().getInt("id");

        String url = "http://600tuvungtoeic.com/index.php?mod=lesson&id=" + String.valueOf(id);

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient());

    }

}
