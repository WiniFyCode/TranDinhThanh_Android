package com.thanh.trandinhthanh_android;

import java.io.Serializable;

public class ProductModel implements Serializable {

    public String masp,tensp,hinhsp;
    public int giasp;

    public ProductModel(String masp, String tensp, String hinhsp, int giasp) {
        this.masp = masp;
        this.tensp = tensp;
        this.hinhsp = hinhsp;
        this.giasp = giasp;
    }
}
