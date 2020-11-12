package com.e.myapplication.session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.se.omapi.Session;
import android.util.Log;

import com.e.myapplication.admin.HomeAdminActivity;
import com.e.myapplication.pembeli.HomePembeliActivity;

public class PrefSetting {

    public static String _id;
    public static String username;
    public static String namalengkap;
    public static String email;
    public static String noTelp;
    public static String role;
    Activity activity;

    public PrefSetting(Activity activity) {
        this.activity = activity;
    }

    public SharedPreferences getSharePrefrances() {
        SharedPreferences preferences = activity.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        return preferences;
    }

    public void isiLogin(SessionManager session, SharedPreferences pref) {
        session = new SessionManager(activity);
        if (session.isLoggedIn()) {
            pref = getSharePrefrances();
            _id = pref.getString("_id", "");
            username = pref.getString("username", "");
            namalengkap = pref.getString("namalengkap", "");
            email = pref.getString("email", "");
            noTelp = pref.getString("noTelp", "");
            role = pref.getString("role", "");
        } else {
            session.setLogin(false);
            session.setSessid(0);
            Intent i = new Intent(activity, activity.getClass());
            activity.startActivity(i);
            activity.finish();
        }
    }


    public void checkLogin(SessionManager session, SharedPreferences pref) {
        session = new SessionManager(activity);
        _id = pref.getString("_id", "");
        username = pref.getString("username", "");
        namalengkap = pref.getString("namalengkap", "");
        email = pref.getString("email", "");
        noTelp = pref.getString("noTelp", "");
        role = pref.getString("role", "");
        System.out.println("Role =" + role);
        if (session.isLoggedIn()) {
            if (role.equals("1")) {
                Intent i = new Intent(activity, HomeAdminActivity.class);
                activity.startActivity(i);
                activity.finish();
            } else {
                Intent i = new Intent(activity, HomePembeliActivity.class);
                activity.startActivity(i);
                activity.finish();
            }
        }
    }

    public void storeRegIdSharedPreferences(Context context, String _id, String username,
                                            String namalengkap, String email, String noTelp, String role, SharedPreferences prefs) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("_id", _id);
        editor.putString("username", username);
        editor.putString("namalengkap", namalengkap);
        editor.putString("email", email);
        editor.putString("noTelp", noTelp);
        editor.putString("role", role);
        editor.commit();
    }

}
