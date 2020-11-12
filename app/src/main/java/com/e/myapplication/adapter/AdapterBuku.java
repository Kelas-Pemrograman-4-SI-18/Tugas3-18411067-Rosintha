package com.e.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.myapplication.R;
import com.e.myapplication.model.ModelBuku;
import com.e.myapplication.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBuku extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelBuku> item;

    public AdapterBuku(Activity activity, List<ModelBuku> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_buku, null);


        TextView judul = (TextView) convertView.findViewById(R.id.txtJudulBuku);
        TextView pengarang     = (TextView) convertView.findViewById(R.id.txtPengarang);
        TextView penerbit          = (TextView) convertView.findViewById(R.id.txtPenerbit);
        TextView harga         = (TextView) convertView.findViewById(R.id.txtHargaBuku);

        ImageView gambarBuku         = (ImageView) convertView.findViewById(R.id.gambarBuku);

        judul.setText(item.get(position).get_id());
        pengarang.setText(item.get(position).getKodejam());
        penerbit.setText(item.get(position).getMerkjam());
        harga.setText("Rp." + item.get(position).getHargajam());
        Picasso.get().load(BaseURL.baseURL + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarBuku);
        return convertView;
    }

}
