package com.example.ashish.newsrella.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ashish.newsrella.Activity.TopHeadlineActivity;
import com.example.ashish.newsrella.Business.Sources;
import com.example.ashish.newsrella.R;
import java.util.List;

/**
 * Created by ashish on 18/12/17.
 */

public class NewsSourceAdapter extends  RecyclerView.Adapter<NewsSourceAdapter.NewsSourcesViewHolder> {

    class NewsSourcesViewHolder extends RecyclerView.ViewHolder {
        TextView sourceName;
        TextView category;
        TextView desctiption;
        CardView sources;

        NewsSourcesViewHolder(View v) {
            super(v);
            sourceName = v.findViewById(R.id.source_name);
            category = v.findViewById(R.id.category);
            sources = v.findViewById(R.id.sources);
            desctiption = v.findViewById(R.id.description);
        }
    }

    private Activity activity;
    private List<Sources> sourceLists;


    public NewsSourceAdapter(Activity activity, List<Sources> sourceLists) {
        this.activity = activity;
        this.sourceLists = sourceLists;
    }

    @Override
    public NewsSourcesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sourceitem, parent, false);
        NewsSourcesViewHolder viewHolder = new NewsSourcesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsSourcesViewHolder holder, final int position) {

        holder.sourceName.setText(sourceLists.get(position).getName());
        holder.desctiption.setText(sourceLists.get(position).getDescription());
        holder.category.setText(sourceLists.get(position).getCategory());
        holder.sources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(activity,TopHeadlineActivity.class);
                intent.putExtra("sourceId",sourceLists.get(position).getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sourceLists.size();
    }
}
