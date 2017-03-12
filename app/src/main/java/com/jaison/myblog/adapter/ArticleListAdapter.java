package com.jaison.myblog.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaison.myblog.R;
import com.jaison.myblog.model.Article;
import com.jaison.myblog.viewHolder.ArticleItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleItemViewHolder> {

    List<Article> articleList = new ArrayList<>();
    Helper helper;

    public interface Helper {
        void onArticleClicked(Article article);
    }

    public ArticleListAdapter(Helper helper) {
        this.helper = helper;
    }

    @Override
    public ArticleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating the view - layout_article.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_article, parent, false);
        return new ArticleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleItemViewHolder holder, final int position) {
        holder.articleName.setText(articleList.get(position).getHeading());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.onArticleClicked(articleList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setData(List<Article> data) {
        this.articleList = data;
        this.notifyDataSetChanged();
    }
}