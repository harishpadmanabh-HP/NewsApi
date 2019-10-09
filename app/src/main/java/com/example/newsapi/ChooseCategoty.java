package com.example.newsapi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChooseCategoty extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private FloatingActionButton fab;
    private RecyclerView categoryrv;
    ArrayList<String> categoryTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_categoty);
        initView();
        categoryTitles=new ArrayList<>();
        addCategories();
       // setRecyclerView();

    }

    private void setRecyclerView() {

    }

    private void addCategories() {
        categoryTitles.add("Buisiness");
        categoryTitles.add("Entertainment");
        categoryTitles.add("General");
        categoryTitles.add("Health");
        categoryTitles.add("Science");
        categoryTitles.add("Sports");
        categoryTitles.add("Technology");

      int[] categoryImages={R.mipmap.bui,
              R.mipmap.news,R.mipmap.general,R.mipmap.health,R.mipmap.sc,R.mipmap.download,
              R.mipmap.it
                                  };
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.HORIZONTAL);
        categoryrv.setLayoutManager(staggeredGridLayoutManager);
        categoryrv.setAdapter(new CategoryRVA(categoryTitles,categoryImages));

    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        categoryrv = (RecyclerView) findViewById(R.id.categoryrv);
    }

    public void fabclicked(View view) {
        startActivity(new Intent(ChooseCategoty.this,NewsActivity.class));
    }

    class CategoryRVA extends RecyclerView.Adapter<CategoryRVA.CategoryRVVH>
    {
        ArrayList<String> categoryTitles;
        int[] categoryImages;

        public CategoryRVA(ArrayList<String> categoryTitles, int[] categoryImages) {
            this.categoryTitles = categoryTitles;
            this.categoryImages = categoryImages;

            Log.e("categoryTitles", String.valueOf(categoryTitles.size()));
            Log.e("categoryImages", String.valueOf(categoryImages.length));


        }

        @NonNull
        @Override
        public CategoryRVVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecategory, parent, false);
            return new CategoryRVVH(root);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(@NonNull final CategoryRVVH holder, int position) {

            holder.mview.setBackgroundResource(R.drawable.rvitem);
            holder.cattitle.setText(categoryTitles.get(position));
            holder.catimg.setImageResource(categoryImages[position]);
            holder.mview.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    if(!holder.isclicked) {
                        holder.mview.setBackgroundResource(R.drawable.rvselected);
                       holder.mview.setAlpha((float) 0.5);
                        //holder.mview.setScaleY((float) 1.0);
                        holder.mview.setElevation(50);
                        holder.isclicked = true;

                    }
                    else if(holder.isclicked)
                    {
                        holder.mview.setBackgroundResource(R.drawable.rvitem);
                        holder.isclicked = false;
                        holder.mview.setElevation(0);

                        holder.mview.setAlpha((float) 1.0);

                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return categoryTitles.size();
        }

        class CategoryRVVH extends RecyclerView.ViewHolder{
              ImageView catimg;
              TextView cattitle;
              View mview;
              Boolean isclicked;
            public CategoryRVVH(@NonNull View itemView) {
                super(itemView);
                isclicked=false;
                mview=itemView;
                catimg=itemView.findViewById(R.id.catimage);
                cattitle=itemView.findViewById(R.id.catname);


            }
        }
    }
}
