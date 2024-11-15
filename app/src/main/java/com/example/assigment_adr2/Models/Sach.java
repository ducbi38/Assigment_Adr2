package com.example.assigment_adr2.Models;

public class Sach {
    public String maSach;
    public String maLoai;
    public String tenSach;
    public Integer giaThue;

    public String getMaSach() {
        return maSach;
    }

    public Sach() {
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public double getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public Sach(String maSach, String maLoai, String tenSach, int giaThue) {
        this.maSach = maSach;
        this.maLoai = maLoai;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
    }
}
