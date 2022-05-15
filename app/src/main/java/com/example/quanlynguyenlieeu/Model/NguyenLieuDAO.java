package com.example.quanlynguyenlieeu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlynguyenlieeu.DB.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class NguyenLieuDAO {
    private SQLiteDatabase db;

    public NguyenLieuDAO(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    Cursor readCursor(String tblName) {
        String query = "select * from " + tblName;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public List<NguyenLieu> getNguyenLieuList() {
        List<NguyenLieu> nguyenLieuList = new ArrayList<>();
        Cursor cursor = readCursor(NguyenLieu.TABLE_NAME);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
                NguyenLieu nguyenLieu = new NguyenLieu();
                nguyenLieu.setMaNL(cursor.getInt(0));
                nguyenLieu.setTenNL(cursor.getString(1));
                nguyenLieu.setNhaCC(cursor.getString(2));
                nguyenLieu.setLoaiNL(cursor.getString(3));
                nguyenLieu.setDonGia(cursor.getInt(4));
                nguyenLieu.setSoLuong(cursor.getInt(5));
                nguyenLieu.setDonVi(cursor.getString(6));
                nguyenLieu.setHinhAnh(cursor.getBlob(7));
                nguyenLieuList.add(nguyenLieu);
            } while (cursor.moveToNext());
        }
        return nguyenLieuList;
    }

    public void insertUpdateNguyenLieu(NguyenLieu nguyenLieu, boolean isUpdate) {
        ContentValues values = new ContentValues();
        values.put(NguyenLieu.TEN_NL, nguyenLieu.getTenNL());
        values.put(NguyenLieu.NHA_CC, nguyenLieu.getNhaCC());
        values.put(NguyenLieu.LOAI_NL, nguyenLieu.getLoaiNL());
        values.put(NguyenLieu.DON_GIA, nguyenLieu.getDonGia());
        values.put(NguyenLieu.SO_LUONG, nguyenLieu.getSoLuong());
        values.put(NguyenLieu.DON_VI, nguyenLieu.getDonVi());
        values.put(NguyenLieu.HINH_ANH, nguyenLieu.getHinhAnh());
        if (isUpdate) {
            db.update(NguyenLieu.TABLE_NAME, values, NguyenLieu.MA_NL + "=?", new String[]{String.valueOf(nguyenLieu.getMaNL())});
        } else {
            db.insert(NguyenLieu.TABLE_NAME, null, values);
        }
    }

    public void delNguyenLieu(int maNL) {
        db.delete(NguyenLieu.TABLE_NAME, NguyenLieu.MA_NL + "=?", new String[]{String.valueOf(maNL)});
    }

    public void delAll() {
        db.delete(NguyenLieu.TABLE_NAME, null, null);
    }
}
