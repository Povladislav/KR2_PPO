package com.example.ppo_kr2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ppo_kr2.SQLite.UsersDbManager;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<User> adapter;
    private ArrayList<User> users = new ArrayList<>();
    private UsersDbManager usersDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        listView = findViewById(R.id.listView);

        usersDbManager = new UsersDbManager(this);
        usersDbManager.openDb();

        adapter = new UsersAdapter(this);
        listView.setAdapter(adapter);
        users.addAll(usersDbManager.readFromDb());
        //
    }

    public void buttonBackClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private class UsersAdapter extends ArrayAdapter<User> {

        public UsersAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1, users);
        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            User user = getItem(position);
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view = inflater.inflate(R.layout.add_new_field, null);
            TextView textViewID = (TextView) view.findViewById(R.id.textViewID);
            textViewID.setText(Integer.toString(user.getId()));
            TextView textViewClient = (TextView) view.findViewById(R.id.textViewUsername);
            textViewClient.setText(user.getUsername());
            TextView textViewDate = (TextView) view.findViewById(R.id.textViewScore);
            textViewDate.setText(Integer.toString(user.getScore()));
            return view;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usersDbManager.closeDb();
    }
}
