package com.example.q_thjen.jsoupdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Model> list = new ArrayList<>();
    Adapter adapter;

    String url = "http://600tuvungtoeic.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this, list);

        recyclerView.setAdapter(adapter);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String name = "";
                String image = "";

                Document doc = Jsoup.parse(response);

                if ( doc != null ) {

                    /** <td name="thjen"> ==> td[name=thjen]
                     *  <div class="thjen"> ==> div[class=thjen] or div.thjen(if nó là class) **/

                    Elements elements = doc.select("div[class=gallery-item]");

                    for (Element element : elements ) { // chạy trong chiều dài của elements

                        Element elementName = element.getElementsByTag("h3").first();
                        Element elementImage = element.getElementsByTag("img").first();

                        if (elementName != null ) {

                            name = elementName.text();
                        }

                        if (elementImage != null ) {

                            image = elementImage.attr("src");
                        }

                        list.add(new Model(name, image));
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }

}
