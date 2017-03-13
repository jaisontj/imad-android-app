package com.jaison.myblog.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jaison.myblog.network.ApiManager;
import com.jaison.myblog.model.AuthenticationRequest;
import com.jaison.myblog.network.CustomResponseListener;
import com.jaison.myblog.model.ErrorResponse;
import com.jaison.myblog.model.MessageResponse;
import com.jaison.myblog.R;

public class AuthenticationActivity extends BaseActivity {

    EditText username, password;

    public static void startActivity(Activity startingActivity) {
        Intent intent = new Intent(startingActivity, AuthenticationActivity.class);
        startingActivity.startActivity(intent);

        //To clear the stack, so that the user cannot go back to the authentication activity on hardware back press
        startingActivity.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sign In or Register");
        setContentView(R.layout.activity_authentication);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        Button signInButton = (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormValid()) {
                    performSignIn();
                }
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormValid()) {
                    performRegistration();
                }
            }
        });
    }

    private Boolean isFormValid() {
        if (username.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Username cannot be left empty", Toast.LENGTH_LONG).show();
            return false;
        }

        if (password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Password cannot be left empty", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void performSignIn() {
        showProgressDialog(true);
        ApiManager.getApiInterface().login(new AuthenticationRequest(username.getText().toString().trim(), password.getText().toString().trim()))
                .enqueue(new SignInResponseListener());
    }

    private class SignInResponseListener extends CustomResponseListener<MessageResponse> {

        @Override
        public void onSuccessfulResponse(MessageResponse response) {
            showProgressDialog(false);
            ArticleListActivity.startActivity(AuthenticationActivity.this);
        }

        @Override
        public void onFailureResponse(ErrorResponse errorResponse) {
            showProgressDialog(false);
            showAlert("SignIn Failed", errorResponse.getError());
        }
    }

    private void performRegistration() {
        showProgressDialog(true);
        ApiManager.getApiInterface().registration(new AuthenticationRequest(username.getText().toString().trim(), password.getText().toString().trim()))
                .enqueue(new RegistrationResponseListener());
    }

    private class RegistrationResponseListener extends CustomResponseListener<MessageResponse> {

        @Override
        public void onSuccessfulResponse(MessageResponse response) {
            showProgressDialog(false);
            showAlert("Welcome", response.getMessage());
        }

        @Override
        public void onFailureResponse(ErrorResponse errorResponse) {
            showProgressDialog(false);
            showAlert("Registration Failed", errorResponse.getError());
        }
    }

}
