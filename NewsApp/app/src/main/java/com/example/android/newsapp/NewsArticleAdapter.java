package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsArticleAdapter extends ArrayAdapter<NewsArticle>{
    public NewsArticleAdapter(Context context, List<NewsArticle> articles) {
        super(context, 0, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final NewsArticle article = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        LinearLayout listItem = (LinearLayout) convertView.findViewById(R.id.list_item);
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getWebURL()));

                if (sendIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(sendIntent);
                }
            }
        });

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(article.getTitle());

        TextView section = (TextView) convertView.findViewById(R.id.section);
        section.setText(article.getSection());

        TextView author = (TextView) convertView.findViewById(R.id.author);
        if("".equals(article.getAuthor()))
            author.setVisibility(View.GONE);
        else
            author.setText(article.getAuthor());

        TextView datePublished = (TextView) convertView.findViewById(R.id.date_published);
        if(article.getPublishDate() == null)
            datePublished.setVisibility(View.GONE);
        else
            datePublished.setText(getDateString(article.getPublishDate()));

        return convertView;
    }

    private String getDateString(Date date) {
        if(date == null)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        return formatter.format(date);
    }
}
