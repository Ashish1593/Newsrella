package com.example.ashish.newsrella.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.ashish.newsrella.Adapters.NewsSourceAdapter;
import com.example.ashish.newsrella.Business.NewsSourcesResponse;
import com.example.ashish.newsrella.Business.Sources;
import com.example.ashish.newsrella.Constants.Constants;
import com.example.ashish.newsrella.NetworkUtils.ApiClient;
import com.example.ashish.newsrella.NetworkUtils.ApiInterface;
import com.example.ashish.newsrella.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = findViewById(R.id.rv_news_sources);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<NewsSourcesResponse> call = apiService.getNewsSources(Constants.API_KEY);
        call.enqueue(new Callback<NewsSourcesResponse>() {
            @Override
            public void onResponse(Call<NewsSourcesResponse> call, Response<NewsSourcesResponse> response) {
               List<Sources> sources = response.body().getSources();
                recyclerView.setAdapter(new NewsSourceAdapter(MainActivity.this,sources));
                Log.d(TAG, "Number of news Sources received: " + sources.size());
            }

            @Override
            public void onFailure(Call<NewsSourcesResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}






