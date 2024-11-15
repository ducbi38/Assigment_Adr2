package com.example.assigment_adr2;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.assigment_adr2.Adapter.LoaiSachAdapter;
import com.example.assigment_adr2.Models.LoaiSach;
import com.example.assigment_adr2.DAO.LoaiSachDao;

public class CategoryActivity extends AppCompatActivity {
    com.example.assigment_adr2.DAO.LoaiSachDao loaiSachDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView= findViewById(R.id.rc_category);

        loaiSachDao = new LoaiSachDao(this);
        List<LoaiSach> listloaisach = loaiSachDao.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(this,listloaisach);
        recyclerView.setAdapter(loaiSachAdapter);

    }
}