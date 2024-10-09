package com.thanh.trandinhthanh_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imgSlide;
    SearchView searchView;
    RecyclerView recyclerView;

    ArrayList<ProductModel> productData;
    ProductADAPTER productADAPTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imgSlide = findViewById(R.id.imgSlide);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.rcvProduct);

        productData = new ArrayList<>();
        productADAPTER = new ProductADAPTER(this, productData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(productADAPTER);

        loadsp();
    }

    private void loadsp() {
        // B3:
        productData.clear();
        Response.Listener<String> thanhcong = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String giasp = jsonObject.getString("giasp");
                        giasp = giasp.replace(",", "");
                        String tensp = new String(jsonObject.getString("tensp").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                        productData.add(new ProductModel(
                                jsonObject.getString("masp"),
                                tensp,
                                jsonObject.getString("hinhsp"),
                                Integer.parseInt(giasp)
                        ));
                    }
                    productADAPTER.notifyDataSetChanged();
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "LOI" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "thất bại" + error, Toast.LENGTH_SHORT).show();
            }
        };

        // B1: Tạo request trong Volley
        StringRequest stringRequest = new StringRequest("http://192.168.51.174/webapi_electric/laysanpham.php", thanhcong, thatbai);
        // B2: Dùng request với Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}