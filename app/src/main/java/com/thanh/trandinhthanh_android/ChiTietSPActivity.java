package com.thanh.trandinhthanh_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ChiTietSPActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_sp);

        Intent intent = getIntent();

        TextView tvSpName = findViewById(R.id.tvTenSp);
        TextView tvSpPrice = findViewById(R.id.tvGiaSp);
        ImageView imgSP = findViewById(R.id.imgSanPham);

        ProductModel sp = (ProductModel) intent.getSerializableExtra("sp");

        tvSpName.setText(sp.tensp);
        tvSpPrice.setText(sp.giasp);
        Picasso.get().load(sp.hinhsp).into(imgSP);

    }
}