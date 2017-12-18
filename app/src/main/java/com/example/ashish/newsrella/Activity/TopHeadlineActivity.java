package com.example.ashish.newsrella.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.ashish.newsrella.Adapters.NewsHeadLineAdapter;
import com.example.ashish.newsrella.Business.Article;
import com.example.ashish.newsrella.Business.TopHeadlinesResponse;
import com.example.ashish.newsrella.Constants.Constants;
import com.example.ashish.newsrella.NetworkUtils.ApiClient;
import com.example.ashish.newsrella.NetworkUtils.ApiInterface;
import com.example.ashish.newsrella.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashish on 18/12/17.
 */

public class TopHeadlineActivity extends AppCompatActivity {

    private static final String TAG = NewsSourceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_top_headline_layout);
        String source = getIntent().getStringExtra("sourceId");
        final RecyclerView recyclerView = findViewById(R.id.rv_top_headlines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        // fetching top headlines list of selected news source
        Call<TopHeadlinesResponse> call = apiService.getTopHeadlines(source, Constants.API_KEY);
        call.enqueue(new Callback<TopHeadlinesResponse>() {
            @Override
            public void onResponse(Call<TopHeadlinesResponse> call, Response<TopHeadlinesResponse> response) {
                List<Article> articles = response.body().getArticles();
                recyclerView.setAdapter(new NewsHeadLineAdapter(TopHeadlineActivity.this, articles));
                Log.d(TAG, "Number of news Headlines received: " + articles.size());
            }

            @Override
            public void onFailure(Call<TopHeadlinesResponse> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(TopHeadlineActivity.this,"error fetching news sources",Toast.LENGTH_SHORT)
                        .show();
                Log.e(TAG, t.toString());
            }
        });
    }
}
