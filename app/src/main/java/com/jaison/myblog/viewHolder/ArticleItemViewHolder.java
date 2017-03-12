package com.jaison.myblog.viewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jaison.myblog.R;

/**
 * Created by jaison on 12/03/17.
 */

public class ArticleItemViewHolder extends RecyclerView.ViewHolder {

    public TextView articleName;
    public CardView cardView;

    public ArticleItemViewHolder(View itemView) {
        super(itemView);
        articleName = (TextView) itemView.findViewById(R.id.articleName);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
    }
}
