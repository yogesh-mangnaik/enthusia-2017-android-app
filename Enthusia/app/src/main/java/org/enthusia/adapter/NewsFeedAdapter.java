package org.enthusia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.enthusia.R;

/**
 * Created by Yogesh on 11/19/2017.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsViewHolder>{

    String[] newsStrings;
    String[] linkStrings;
    Context context;

    public NewsFeedAdapter(String[] newsStrings,String[] linkStrings, Context context){
        this.newsStrings = newsStrings;
        this.linkStrings = linkStrings;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return newsStrings.length;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_feed_item, viewGroup, false);
        NewsViewHolder pvh = new NewsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder imageViewHolder, int i) {
        imageViewHolder.link.setVisibility(View.GONE);
        imageViewHolder.news.setText(newsStrings[i]);
        if(linkStrings[i].length()!=0){
            imageViewHolder.link.setText(linkStrings[i]);
            imageViewHolder.link.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView news;
        TextView link;
        NewsViewHolder(View itemView) {
            super(itemView);
            news = (TextView)itemView.findViewById(R.id.news);
            link = (TextView)itemView.findViewById(R.id.link);
        }
    }

}
