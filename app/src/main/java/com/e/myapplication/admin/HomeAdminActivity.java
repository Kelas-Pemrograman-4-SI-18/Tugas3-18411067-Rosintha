package com.e.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.e.myapplication.R;
import com.e.myapplication.session.PrefSetting;
import com.e.myapplication.session.SessionManager;
import com.e.myapplication.users.LoginActivity;

public class HomeAdminActivity extends AppCompatActivity {

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;
    CardView cardExit, cardDataJam, cardInputBuku, cardProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePrefrances();

        session = new SessionManager(HomeAdminActivity.this);

        prefSetting.isiLogin(session, prefs);

        cardExit = (CardView) findViewById(R.id.cardExit);
        cardDataJam = (CardView) findViewById(R.id.cardDataJam);
        cardInputBuku = (CardView) findViewById(R.id.cardInputBuku);
        cardProfile = (CardView) findViewById(R.id.cardProfile);

        cardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeAdminActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardDataJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, ActivityDataJam.class);
                startActivity(i);
                finish();
            }
        });
        cardInputBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, InputDataBuku.class);
                startActivity(i);
                finish();
            }
        });
        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, Profile.class);
                startActivity(i);
                finish();
            }
        });
    }
}
