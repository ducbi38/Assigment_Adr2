package com.example.assigment_adr2.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.assigment_adr2.Models.PhieuMuon;
import com.example.assigment_adr2.database.DbHelper;

public class PhieuMuonDao {
    private SQLiteDatabase db;

    public PhieuMuonDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("MaPM", phieuMuon.getMaPM());
        values.put("MaTT", phieuMuon.getMaTT());
        values.put("MaTV", phieuMuon.getMaTV());
        values.put("MaSach", phieuMuon.getMaSach());
        values.put("NgayMuon", phieuMuon.getNgayMuon());
        values.put("TraSach", phieuMuon.getTraSach());
        values.put("TienThue", phieuMuon.getTienThue());
        return db.insert("phieumuon", null, values);
    }

    public int update(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("MaTT", phieuMuon.getMaTT());
        values.put("MaTV", phieuMuon.getMaTV());
        values.put("MaSach", phieuMuon.getMaSach());
        values.put("NgayMuon", phieuMuon.getNgayMuon());
        values.put("TraSach", phieuMuon.getTraSach());
        values.put("TienThue", phieuMuon.getTienThue());
        return db.update("phieumuon", values, "MaPM=?", new String[]{String.valueOf(phieuMuon.getMaPM())});
    }

    public int delete(String maPM) {
        return db.delete("phieumuon", "MaPM=?", new String[]{maPM});
    }
    @SuppressLint("Range")
    public List<PhieuMuon> getAll() {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = db.query("phieumuon", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaPM(cursor.getInt(cursor.getColumnIndex("MaPM")));
            phieuMuon.setMaTT(cursor.getString(cursor.getColumnIndex("MaTT")));
            phieuMuon.setMaTV(cursor.getInt(cursor.getColumnIndex("MaTV")));
            phieuMuon.setMaSach(cursor.getInt(cursor.getColumnIndex("MaSach")));
            phieuMuon.setNgayMuon(cursor.getString(cursor.getColumnIndex("NgayMuon")));
            phieuMuon.setTraSach(cursor.getInt(cursor.getColumnIndex("TraSach")));
            phieuMuon.setTienThue(cursor.getInt(cursor.getColumnIndex("TienThue")));

            list.add(phieuMuon);
        }

        cursor.close();
        return list;
    }

}
