package com.example.assigment_adr2.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import com.example.assigment_adr2.Adapter.LoaiSachAdapter;
import com.example.assigment_adr2.Models.LoaiSach;
import com.example.assigment_adr2.R;


public class LoaiSachFragment extends Fragment {
    com.example.assigment_adr2.DAO.LoaiSachDao loaiSachDao;
    List<LoaiSach> listloaisach;
    LoaiSachAdapter loaiSachAdapter;
    public LoaiSachFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loaisach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView= view.findViewById(R.id.rc_category);

     loaiSachDao = new com.example.assigment_adr2.DAO.LoaiSachDao(getContext());
        listloaisach = loaiSachDao.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
         loaiSachAdapter = new LoaiSachAdapter(getContext(),listloaisach);
        recyclerView.setAdapter(loaiSachAdapter);
        ExtendedFloatingActionButton btnadd = view.findViewById(R.id.btn_add);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddLoaiSachDialog();
            }
        });

    }

    private void showAddLoaiSachDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thêm Loại Sách");

        // Inflate layout dialog chỉ chứa EditText cho mã loại
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_ma_loai, null);
        builder.setView(dialogView);

        EditText etTenLoai = dialogView.findViewById(R.id.etEditLoaiSach);


        builder.setPositiveButton("Thêm", (dialog, which) -> {
            String tenLoai = etTenLoai.getText().toString().trim();

            if (!tenLoai.isEmpty()) {
                    loaiSachDao = new com.example.assigment_adr2.DAO.LoaiSachDao(getContext());
                    long result = loaiSachDao.insert(new LoaiSach("",tenLoai));

                    if (result > 0) {
                        listloaisach.clear();
                        listloaisach.addAll(loaiSachDao.getAll());
                        loaiSachAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm loại sách thất bại", Toast.LENGTH_SHORT).show();
                    }


            } else {
                etTenLoai.setError("Vui lòng nhập tên loại");
            }
        });

        // Nút Hủy
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}