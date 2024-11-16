package com.example.assigment_adr2.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_adr2.DAO.LoaiSachDao;
import com.example.assigment_adr2.Models.LoaiSach;
import com.example.assigment_adr2.R;

import java.util.List;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private final Context context;
    private final List<LoaiSach> listLoaiSach;
    private final LoaiSachDao loaiSachDao;

    public LoaiSachAdapter(Context context, List<LoaiSach> listLoaiSach) {
        this.context = context;
        this.listLoaiSach = listLoaiSach;
        this.loaiSachDao = new LoaiSachDao(context); // Khởi tạo DAO
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiSach loaiSach = listLoaiSach.get(position);
        holder.txtMaLoai.setText("Mã Loại Sách: " + loaiSach.getMaLoai());
        holder.txtTenLoai.setText("Tên Loại Sách: " + loaiSach.getTenSach());

        // Xử lý sự kiện nút "Sửa"
        holder.imgEdit.setOnClickListener(view -> showEditLoaiSachDialog(loaiSach, position));

        // Xử lý sự kiện nút "Xóa"
        holder.imgDelete.setOnClickListener(view -> showDeleteDialog(loaiSach, position));
    }

    @Override
    public int getItemCount() {
        return listLoaiSach.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaLoai, txtTenLoai;
        ImageView imgEdit, imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtId);
            txtTenLoai = itemView.findViewById(R.id.txt_tenloai);
            imgEdit = itemView.findViewById(R.id.img_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }
    }

    // Hiển thị Dialog chỉnh sửa loại sách
    private void showEditLoaiSachDialog(LoaiSach loaiSach, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Chỉnh sửa loại sách");

        // Inflate layout dialog chỉnh sửa
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_loai_sach, null);
        builder.setView(dialogView);

        // Lấy EditText từ dialog layout
        EditText etTenLoai = dialogView.findViewById(R.id.etEditLoaiSach);
        etTenLoai.setText(loaiSach.getTenSach()); // Hiển thị thông tin hiện tại

        // Nút "Lưu"
        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String tenLoaiMoi = etTenLoai.getText().toString().trim();

            if (!tenLoaiMoi.isEmpty()) {
                loaiSach.setTenSach(tenLoaiMoi); // Cập nhật đối tượng
                int result = loaiSachDao.update(loaiSach); // Gọi phương thức cập nhật

                if (result > 0) {
                    listLoaiSach.set(position, loaiSach); // Cập nhật danh sách
                    notifyItemChanged(position); // Làm mới RecyclerView
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Tên loại sách không được để trống", Toast.LENGTH_SHORT).show();
            }
        });

        // Nút "Hủy"
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Hiển thị Dialog xác nhận xóa
    private void showDeleteDialog(LoaiSach loaiSach, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa loại sách: " + loaiSach.getTenSach() + "?");

        // Nút "Xóa"
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            int result = loaiSachDao.delete(loaiSach.getMaLoai()); // Xóa trong cơ sở dữ liệu

            if (result > 0) {
                listLoaiSach.remove(position); // Xóa khỏi danh sách
                notifyItemRemoved(position); // Làm mới RecyclerView
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        // Nút "Hủy"
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
