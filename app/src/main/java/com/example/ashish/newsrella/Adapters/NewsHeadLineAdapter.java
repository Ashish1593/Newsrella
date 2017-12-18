package com.example.ashish.newsrella.Adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashish.newsrella.Business.Article;
import com.example.ashish.newsrella.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ashish on 18/12/17.
 */

public class NewsHeadLineAdapter extends RecyclerView.Adapter<NewsHeadLineAdapter.NewsHeadlinesViewHolder> {

    class NewsHeadlinesViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitle;
        ImageView newsPoster;
        TextView newsContent;
        CardView headlines;

        NewsHeadlinesViewHolder(View v) {
            super(v);
            newsTitle = v.findViewById(R.id.NewsTitle);
            newsPoster = v.findViewById(R.id.NewsImage);
            newsContent = v.findViewById(R.id.Newscontent);
            headlines = v.findViewById(R.id.news_headlines);
        }
    }

    private Activity activity;
    private List<Article> newsLists;

    public NewsHeadLineAdapter(Activity activity, List<Article> newsLists) {
        this.activity = activity;
        this.newsLists = newsLists;
    }

    @Override
    public NewsHeadlinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitem, parent, false);
        NewsHeadlinesViewHolder viewHolder = new NewsHeadlinesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsHeadlinesViewHolder holder, final int position) {

        holder.newsTitle.setText(newsLists.get(position).getTitle());
        String imgurl = newsLists.get(position).getUrlToImage();
        holder.newsContent.setText(newsLists.get(position).getDescription());
        final String url = newsLists.get(position).getUrl();
        Picasso.with(activity)
                .load(imgurl)
                .into(holder.newsPoster);

        holder.headlines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
                intent.launchUrl(activity, Uri.parse(url));
            }

        });

    }


    @Override
    public int getItemCount() {
        return newsLists.size();
    }

}

