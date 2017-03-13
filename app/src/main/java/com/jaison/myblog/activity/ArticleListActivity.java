package com.jaison.myblog.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.jaison.myblog.R;
import com.jaison.myblog.adapter.ArticleListAdapter;
import com.jaison.myblog.model.Article;
import com.jaison.myblog.model.ErrorResponse;
import com.jaison.myblog.network.ApiManager;
import com.jaison.myblog.network.CustomResponseListener;

import java.util.List;

public class ArticleListActivity extends BaseActivity {

    RecyclerView recyclerView;
    ArticleListAdapter articleListAdapter;

    public static void startActivity(Activity startingActivity) {
        Intent intent = new Intent(startingActivity, ArticleListActivity.class);
        startingActivity.startActivity(intent);

        //To clear the stack, so that the user cannot go back to the authentication activity on hardware back press
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        //setting activity title
        setTitle("Article List");

        articleListAdapter = new ArticleListAdapter(new ArticleListAdapter.Helper() {
            @Override
            public void onArticleClicked(Article article) {
                ArticleDetailActivity.startActivity(ArticleListActivity.this, article);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //Step 1 - create an adapter
        recyclerView.setAdapter(articleListAdapter);
        //Step 2 - set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showProgressDialog(true);
        ApiManager.getApiInterface().getArticles()
                .enqueue(new ArticleListResponseListener());
    }

    private class ArticleListResponseListener extends CustomResponseListener<List<Article>> {

        @Override
        public void onSuccessfulResponse(List<Article> response) {
            showProgressDialog(false);
            articleListAdapter.setData(response);
        }

        @Override
        public void onFailureResponse(ErrorResponse errorResponse) {
            showProgressDialog(false);
            showAlert("Failed", errorResponse.getError());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:
                //perform logout
                showProgressDialog(true);
                ApiManager.getApiInterface().logout()
                        .enqueue(new LogoutResponseListener());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class LogoutResponseListener extends CustomResponseListener<Void> {

        @Override
        public void onSuccessfulResponse(Void response) {
            showProgressDialog(false);
            AuthenticationActivity.startActivity(ArticleListActivity.this);
        }

        @Override
        public void onFailureResponse(ErrorResponse errorResponse) {
            showProgressDialog(false);
            showAlert("Log out Failed",errorResponse.getError());
        }
    }
}
