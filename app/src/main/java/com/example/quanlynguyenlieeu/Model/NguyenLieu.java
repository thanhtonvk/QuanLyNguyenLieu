package com.example.quanlynguyenlieeu.Model;

import java.io.Serializable;

public class NguyenLieu implements Serializable {
    private int maNL;
    private String tenNL;
    private String nhaCC;
    private String loaiNL;
    private int donGia;
    private int soLuong;
    private String donVi;
    private byte[] hinhAnh;
    //constants for database
    public static final String TABLE_NAME = "NguyenLieu";
    public static final String MA_NL = "maNL";
    public static final String TEN_NL = "tenNL";
    public static final String NHA_CC = "nhaCC";
    public static final String LOAI_NL = "loaiNL";
    public static final String DON_GIA = "donGia";
    public static final String SO_LUONG = "soLuong";
    public static final String DON_VI = "donVi";
    public static final String HINH_ANH = "hinhAnh";
    public static final String QUERY_CREATE_TABLE = String.format("create table %s(" +
            "%s integer primary key autoincrement," +
            "%s nvarchar(50)," +
            "%s nvarchar(50)," +
            "%s nvarchar(50)," +
            "%s integer," +
            "%s integer," +
            "%s nvarchar(50)," +
            "%s blob)",TABLE_NAME, MA_NL, TEN_NL, NHA_CC, LOAI_NL, DON_GIA, SO_LUONG, DON_VI, HINH_ANH);

    public NguyenLieu(int maNL, String tenNL, String nhaCC, String loaiNL, int donGia, int soLuong, String donVi, byte[] hinhAnh) {
        this.maNL = maNL;
        this.tenNL = tenNL;
        this.nhaCC = nhaCC;
        this.loaiNL = loaiNL;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.hinhAnh = hinhAnh;
    }

    public NguyenLieu() {

    }

    public int getMaNL() {
        return maNL;
    }

    public void setMaNL(int maNL) {
        this.maNL = maNL;
    }

    public String getTenNL() {
        return tenNL;
    }

    public void setTenNL(String tenNL) {
        this.tenNL = tenNL;
    }

    public String getNhaCC() {
        return nhaCC;
    }

    public void setNhaCC(String nhaCC) {
        this.nhaCC = nhaCC;
    }

    public String getLoaiNL() {
        return loaiNL;
    }

    public void setLoaiNL(String loaiNL) {
        this.loaiNL = loaiNL;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return tenNL;
    }
}
