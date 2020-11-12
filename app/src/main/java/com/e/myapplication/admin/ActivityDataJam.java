package com.e.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.myapplication.R;
import com.e.myapplication.adapter.AdapterBuku;
import com.e.myapplication.model.ModelBuku;
import com.e.myapplication.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityDataJam extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterBuku adapter;
    ListView list;

    ArrayList<ModelBuku> newList = new ArrayList<ModelBuku>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_jam);

        getSupportActionBar().setTitle("Data Jam");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list=(ListView) findViewById(R.id.array_list);
        newList.clear();
        adapter = new AdapterBuku(ActivityDataJam.this, newList);
        list.setAdapter(adapter);
        getAllBuku();
    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(ActivityDataJam.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllBuku() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.datajam, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data buku = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelBuku buku = new ModelBuku();
                                    final String _id = jsonObject.getString("_id");
                                    final String kodejam = jsonObject.getString("kodejam");
                                    final String merkjam = jsonObject.getString("merkjam");
                                    final String hargajam = jsonObject.getString("hargajam");
                                    final String gambar = jsonObject.getString("gambar");
                                    buku.setKodejam(kodejam);
                                    buku.setMerkjam(merkjam);
                                    buku.setHargajam(hargajam);
                                    buku.setGambar(gambar);
                                    buku.set_id(_id);

                                   list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(ActivityDataJam.this, EditBukuDanHapusActivity.class);
                                           a.putExtra("kodejam", newList.get(position).getKodejam());
                                           a.putExtra("_id", newList.get(position).get_id());
                                           a.putExtra("merkjam", newList.get(position).getMerkjam());
                                            a.putExtra("hargajam", newList.get(position).getHargajam());
                                           a.putExtra("gambar", newList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                   });
                                    newList.add(buku);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
