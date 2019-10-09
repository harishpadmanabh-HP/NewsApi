package com.example.newsapi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.squareup.picasso.Picasso;

public class NewsDetailed extends AppCompatActivity {

    AppPreferences appPreferences;
    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView collapsingToolbarImageView;
    private Toolbar toolbar;
    TextView newsdetails;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detailed);
        initView();
        setSupportActionBar(toolbar);

        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        Picasso.get().load(appPreferences.getData("newsimage")).into(collapsingToolbarImageView);
        newsdetails.setText(appPreferences.getData("newscontent"));
        toolbarLayout.setTitle(appPreferences.getData("newstitle"));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initView() {
        appBar = findViewById(R.id.app_bar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarImageView = findViewById(R.id.collapsing_toolbar_image_view);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        newsdetails =  findViewById(R.id.newsdetails);
    }
}
