package com.example.newsapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapi.Retro.Retro;
import com.example.newsapi.model.NewsEverythingModel;
import com.google.android.material.button.MaterialButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsHomeFragment extends Fragment {
/*
* check for tutorial backdrop
* https://codelabs.developers.google.com/codelabs/mdc-104-java/#3
*
* https://codelabs.developers.google.com/codelabs/mdc-104-java/#5
* */
AppPreferences appPreferences;

Context context;
private boolean isLoading = false;
    private boolean isLastPage = false;
    private int page = 1;
    NewsAdapter newsAdapter;
RecyclerView recyclerView;
MaterialButton buisiness;
    NavigationIconClickListener navigationIconClickListener;
private List<NewsEverythingModel.ArticlesBean> newsList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view= inflater.inflate(R.layout.sparewithbackdrophome, container, false);
        final Toolbar toolbar = view.findViewById(R.id.app_bar);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));

        navigationIconClickListener=new NavigationIconClickListener(
               getContext(),
               view.findViewById(R.id.product_grid));

        setUpToolbar(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.product_grid).setBackgroundResource(R.drawable.shr_product_grid_background_shape);

           // recyclerView.setBackgroundResource(R.drawable.shr_product_grid_background_shape);

        }
        buisiness=view.findViewById(R.id.buisness);
      buisiness.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              buisiness.setBackgroundResource(R.drawable.backdropselcted);
              //buisiness.setTextColor(R.color.oink);
          }
      });
        recyclerView=view.findViewById(R.id.recycler_view);
        loadnews();


//        try{
//            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
//                  recyclerView.setLayoutManager(staggeredGridLayoutManager);
//
//                  newsAdapter=new NewsAdapter(context);
//                  recyclerView.setAdapter(newsAdapter);
//                  recyclerView.addOnScrollListener(new PaginationScrollListener() {
//                      @Override
//                      protected void loadMoreItems() {
//                          isLoading = true;
//                          if (!isLastPage) {
//                              new Handler().postDelayed(new Runnable() {
//                                  @Override
//                                  public void run() {
//                                      loadData(page);
//                                  }
//
//
//                              }, 200);
//                          }
//                      }
//
//                      @Override
//                      public boolean isLastPage() {
//                          return isLastPage;
//                      }
//
//                      @Override
//                      public boolean isLoading() {
//                          return isLoading;                      }
//                  });
//
//
//            loadData(page);
//        }catch (Exception e)
//        {
//            Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
//        }




  return view;
    }

//    private void loadData(int page) {
//
//        Retro retro=new Retro();
//        retro.getApi().newsEverythingCallpagination(page).enqueue(new Callback<NewsEverythingModel>() {
//            @Override
//            public void onResponse(Call<NewsEverythingModel> call, Response<NewsEverythingModel> response) {
//                NewsEverythingModel newsEverythingModel=response.body();
//                resultAction(newsEverythingModel);
//
//            }
//
//            @Override
//            public void onFailure(Call<NewsEverythingModel> call, Throwable t) {
//
//            }
//        });
//
//    }
//    private void resultAction(NewsEverythingModel model) {
//       // progressBar.setVisibility(View.INVISIBLE);
//        isLoading = false;
//        if (model != null) {
//            newsAdapter.addItems(model.getArticles());
//            if (model.getPage() == model.getTotal_pages()) {
//                isLastPage = true;
//            } else {
//                page = model.getPage() + 1;
//            }
//        }
//    }


    private void loadnews() {
        final Retro retro=new Retro();
        retro.getApi().newsEverythingCall().enqueue(new Callback<NewsEverythingModel>() {
            @Override
            public void onResponse(Call<NewsEverythingModel> call, Response<NewsEverythingModel> response) {
                 newsList=response.body().getArticles();
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                  recyclerView.setLayoutManager(staggeredGridLayoutManager);
                  recyclerView.setAdapter(new NewsAdapter(newsList));



            }

            @Override
            public void onFailure(Call<NewsEverythingModel> call, Throwable t) {

            }
        });

    }


    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        assert activity != null;
        activity.setSupportActionBar(toolbar);
            //toolbar.setNavigationIcon(R.drawable.ic_gps_fixed_black_24dp);


        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                getContext(),
                view.findViewById(R.id.product_grid),
                new AccelerateDecelerateInterpolator(),
                getContext().getResources().getDrawable(R.drawable.ic_gps_fixed_black_24dp), // Menu open icon
                getContext().getResources().getDrawable(R.drawable.shr_close_menu))); // Menu close icon


    }

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsRVVH>
{
    private List<NewsEverythingModel.ArticlesBean> newsList;
    Context context;
    NewsEverythingModel newsEverythingModel;

    public NewsAdapter(Context context, NewsEverythingModel newsEverythingModel) {
        this.context = context;
        this.newsEverythingModel = newsEverythingModel;
    }


    public NewsAdapter(Context context) {
        this.context = context;
    }

    public NewsAdapter(List<NewsEverythingModel.ArticlesBean> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsRVVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlenewsitem, parent, false);
       return new NewsRVVH(root);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVVH holder, final int position) {
        Picasso.get().load(newsList.get(position).getUrlToImage()).into(holder.newsimg);
         holder.newstitle.setText(newsList.get(position).getTitle());
         holder.newsdec.setText(newsList.get(position).getDescription());

         holder.mview.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 appPreferences.saveData("newsimage",newsList.get(position).getUrlToImage());
                 appPreferences.saveData("newstitle",newsList.get(position).getTitle());
                 appPreferences.saveData("newsauthor",newsList.get(position).getAuthor());
                 appPreferences.saveData("newscontent",newsList.get(position).getDescription());

                 startActivity(new Intent(getContext(),NewsDetailed.class));




             }
         });



    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsRVVH extends  RecyclerView.ViewHolder{
ImageView newsimg;
TextView newstitle,newsdec;
View mview;


        public NewsRVVH(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
            newsimg=mview.findViewById(R.id.newsimageitem);
            newstitle=mview.findViewById(R.id.newstitleitem);
            newsdec=mview.findViewById(R.id.newsdescitem);


        }
    }
    public void setItems(List<NewsEverythingModel.ArticlesBean> items){
        newsList = items;
        notifyDataSetChanged();
    }

    public void addItems(List<NewsEverythingModel.ArticlesBean> items){
        newsList.addAll(items);
        notifyDataSetChanged();
    }
}
}
