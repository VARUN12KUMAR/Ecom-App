package com.example.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

public class Category extends AppCompatActivity {
    private CardView clot, radio, news,mobile, tv, adver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        clot = (CardView) findViewById(R.id.clot);
        radio = (CardView) findViewById(R.id.radio);
        news = (CardView) findViewById(R.id.news);
        mobile = (CardView) findViewById(R.id.phone);
        tv = (CardView) findViewById(R.id.tv);
        adver = (CardView) findViewById(R.id.adver);

        clot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, addproduct.class);
                intent.putExtra("category", "tShirts");
                startActivity(intent);
            }
        });
        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, addproduct.class);
                intent.putExtra("category", "Music");
                startActivity(intent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, addproduct.class);
                intent.putExtra("category", "news");
                startActivity(intent);
            }
        });

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, addproduct.class);
                intent.putExtra("category", "mobile");
                startActivity(intent);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, addproduct.class);
                intent.putExtra("category", "Tv");
                startActivity(intent);
            }
        });
        adver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, addproduct.class);
                intent.putExtra("category", "advertisement");
                startActivity(intent);
            }
        });


    }
}
