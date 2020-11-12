package com.e.myapplication.users;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.myapplication.R;
import com.e.myapplication.pembeli.HomePembeliActivity;
import com.e.myapplication.server.BaseURL;
import com.ornach.nobobutton.NoboButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegistrasiActivity extends AppCompatActivity {

    Button btnBackLogin;
    NoboButton btnRegistrasi;
    EditText edtusername, edtemail, edtnamalengkap, edtnoTelp, edtpassword;
    ProgressDialog pDialog;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        getSupportActionBar().hide();


        mRequestQueue = Volley.newRequestQueue(this);

        edtusername = (EditText) findViewById(R.id.edtusername);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtnamalengkap = (EditText) findViewById(R.id.edtnamalengkap);
        edtnoTelp = (EditText) findViewById(R.id.edtnoTelp);
        edtpassword = (EditText) findViewById(R.id.edtpassword);

        btnBackLogin = (Button) findViewById(R.id.btnBackLogin);
        btnRegistrasi = (NoboButton) findViewById(R.id.btnRegistrasi);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strusername = edtusername.getText().toString();
                String stremail = edtemail.getText().toString();
                String strnamalengkap = edtnamalengkap.getText().toString();
                String strnoTelp = edtnoTelp.getText().toString();
                String strpassword = edtpassword.getText().toString();

                if (strusername.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong!", Toast.LENGTH_LONG).show();

                } else if (strnamalengkap.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong!", Toast.LENGTH_LONG).show();

                } else if (stremail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong!", Toast.LENGTH_LONG).show();

                } else if (strnoTelp.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nomor Telpon Tidak Boleh Kosong!", Toast.LENGTH_LONG).show();

                } else if (strpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong!", Toast.LENGTH_LONG).show();
                } else {
                    registrasi(strusername, strnamalengkap, stremail, strnoTelp, strpassword);
                }
            }
        });

    }


    public void registrasi(String username, String namalengkap, String email, String noTelp, String password) {


// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
        params.put("noTelp", noTelp);
        params.put("namalengkap", namalengkap);
        params.put("role", "2");


        pDialog.setMessage("Mohon Tunggu....");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.register, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

}