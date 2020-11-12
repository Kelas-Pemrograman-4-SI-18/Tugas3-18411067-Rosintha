package com.e.myapplication.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.e.myapplication.R;
import com.e.myapplication.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailJam extends AppCompatActivity {

    EditText edtKodejam, edtMerkjam, edtHargajam;
    ImageView imgGambarBuku;

    String strKodejam, stre, strMerkjam, strHargajam, _id, strGamabr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jam);

        edtKodejam= (EditText) findViewById(R.id.edtKodejam);
        edtMerkjam = (EditText) findViewById(R.id.edtMerkjam);
        edtHargajam = (EditText) findViewById(R.id.edtHargajam);

        imgGambarBuku = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strKodejam = i.getStringExtra("kodejam");
        strMerkjam = i.getStringExtra("merkjam");
        strHargajam = i.getStringExtra("hargajam");
        strGamabr = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtKodejam.setText(strKodejam);
        edtMerkjam.setText(strMerkjam);
        edtHargajam.setText(strHargajam);
        Picasso.get().load(BaseURL.baseURL + "gambar/" + strGamabr)
                .into(imgGambarBuku);


    }
}
