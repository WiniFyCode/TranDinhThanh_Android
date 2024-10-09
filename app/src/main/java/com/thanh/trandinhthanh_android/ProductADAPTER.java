package com.thanh.trandinhthanh_android;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductADAPTER extends RecyclerView.Adapter<PRODUCT_VIEWHOLDER> {

     public Context context;
    public ArrayList <ProductModel> productData;

    public ProductADAPTER(Context context, ArrayList<ProductModel> productData) {
        this.context = context;
        this.productData = productData;
    }

    @NonNull
    @Override
    public PRODUCT_VIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_product_layout, null);
        return new PRODUCT_VIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PRODUCT_VIEWHOLDER holder, int position) {
        ProductModel productModel = productData.get(position);
        holder.tvSpName.setText(productModel.tensp);
        holder.tvSpPrice.setText(productModel.giasp + " VND");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSPActivity.class);
                intent.putExtra("masp", productModel.masp);
                intent.putExtra("tensp", productModel.tensp);
                intent.putExtra("giasp", productModel.giasp);
                intent.putExtra("hinhsp", productModel.hinhsp);

                context.startActivity(intent);
            }
        });

        Picasso.get().load("http://192.168.51.174/webapi_electric/hinhanh/" + productModel.hinhsp).into(holder.imgSp);

    }

    @Override
    public int getItemCount() {
        return productData.size();
    }
}

class PRODUCT_VIEWHOLDER extends RecyclerView.ViewHolder {

    ImageView imgSlide;
    ImageView imgSp;
    TextView tvSpName, tvSpPrice;

    public PRODUCT_VIEWHOLDER(@NonNull View itemView) {
        super(itemView);

        imgSlide = itemView.findViewById(R.id.imgSlide);
        imgSp = itemView.findViewById(R.id.imgSp);
        tvSpName = itemView.findViewById(R.id.tvSpName);
        tvSpPrice = itemView.findViewById(R.id.tvSpPrice);

    }
}
