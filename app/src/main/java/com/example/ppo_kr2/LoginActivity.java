package com.example.ppo_kr2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ppo_kr2.SQLite.UsersDbManager;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private Button buttonGo;
    private TextView textViewWarning;
    private UsersDbManager usersDbManager;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        editTextLogin = findViewById(R.id.editTextLogin);
        buttonGo = findViewById(R.id.buttonGo);
        textViewWarning = findViewById(R.id.textViewWarning);

        usersDbManager = new UsersDbManager(this);
        usersDbManager.openDb();

        editTextLogin.addTextChangedListener(new com.example.ppo_kr2.TextValidator(editTextLogin) {
            @Override
            public void validate(TextView textView, String text) {
                if(!emptyInput()) {
                    buttonGo.setBackgroundColor(Color.parseColor("#A11C1C"));
                    textViewWarning.setVisibility(View.INVISIBLE);
                } else {
                    buttonGo.setBackgroundColor(Color.parseColor("#9F9F9F"));
                }
            }
        });
    }

    public void buttonGoClick(View view) {
        if(emptyInput()) {
            textViewWarning.setVisibility(View.VISIBLE);
        } else {
            textViewWarning.setVisibility(View.INVISIBLE);
            String username = editTextLogin.getText().toString();
            user = usersDbManager.checkDb(username);
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }
    }

    public void buttonRatingClick(View view) {
        Intent intent = new Intent(this, RatingActivity.class);
        startActivity(intent);
    }


    public boolean emptyInput() {
        String username = editTextLogin.getText().toString();
        if(username.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usersDbManager.closeDb();
    }
}
