package com.jaison.myblog.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.jaison.myblog.R;
import com.jaison.myblog.model.Article;

public class ArticleDetailActivity extends AppCompatActivity {

    private static final String ARTICLE_KEY = "articleKey";

    public static void startActivity(Activity startingActivity, Article article) {
        Intent intent = new Intent(startingActivity, ArticleDetailActivity.class);
        intent.putExtra(ARTICLE_KEY,article);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        TextView articleTitle = (TextView) findViewById(R.id.articleTitle);
        TextView articleBody = (TextView) findViewById(R.id.articleBody);

        if (getIntent() != null) {
            Article article = getIntent().getParcelableExtra(ARTICLE_KEY);
            articleTitle.setText(article.getTitle());
            articleBody.setText(article.getContent());

            setTitle(article.getHeading());
        }

        //Setting back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
