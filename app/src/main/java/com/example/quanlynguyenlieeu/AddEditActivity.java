package com.example.quanlynguyenlieeu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynguyenlieeu.Model.NguyenLieu;
import com.example.quanlynguyenlieeu.Model.NguyenLieuDAO;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddEditActivity extends AppCompatActivity {

    EditText edtTenNL, edtNhaCC, edtLoaiNL, edtDonGia, edtSoLuong, edtDonVi;
    ImageView imgHinhAnh;
    Button btnLuu, btnHuy;
    private static final int REQUEST_GALERY = 71;
    NguyenLieu nguyenLieu;
    NguyenLieuDAO nguyenLieuDAO;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        anhXaView();
        nguyenLieuDAO = new NguyenLieuDAO(AddEditActivity.this);
        flag = getIntent().getBooleanExtra("flag", false);
        if (flag) {
            nguyenLieu = (NguyenLieu) getIntent().getSerializableExtra("NguyenLieu");
            edtTenNL.setText(nguyenLieu.getTenNL());
            edtNhaCC.setText(nguyenLieu.getNhaCC());
            edtLoaiNL.setText(nguyenLieu.getLoaiNL());
            edtDonGia.setText(nguyenLieu.getDonGia() + "");
            edtSoLuong.setText(nguyenLieu.getSoLuong() + "");
            edtDonVi.setText(nguyenLieu.getDonVi() + "");
            Bitmap bitmap = Utils.getImage(nguyenLieu.getHinhAnh());
            imgHinhAnh.setImageBitmap(bitmap);
        } else {
            nguyenLieu = new NguyenLieu();
        }
        imgHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_GALERY);
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenNL = edtTenNL.getText().toString();
                String nhaCC = edtNhaCC.getText().toString();
                String loaiNL = edtLoaiNL.getText().toString();
                String donGia = edtDonGia.getText().toString();
                String soLuong = edtSoLuong.getText().toString();
                String donVi = edtDonVi.getText().toString();
                if (tenNL.equals("") || nhaCC.equals("") || loaiNL.equals("") || donVi.equals("") || donGia.equals("") || soLuong.equals("")) {
                    Toast.makeText(getApplicationContext(), "Thông tin không được bỏ trống", Toast.LENGTH_LONG).show();
                } else {
                    nguyenLieu.setTenNL(tenNL);
                    nguyenLieu.setNhaCC(nhaCC);
                    nguyenLieu.setDonVi(donVi);
                    nguyenLieu.setDonGia(Integer.parseInt(donGia));
                    nguyenLieu.setSoLuong(Integer.parseInt(soLuong));
                    nguyenLieu.setLoaiNL(loaiNL);
                    if (flag) {
                        nguyenLieuDAO.insertUpdateNguyenLieu(nguyenLieu, true);
                    } else {
                        nguyenLieuDAO.insertUpdateNguyenLieu(nguyenLieu, false);
                    }
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        });
    }

    private void anhXaView() {
        edtTenNL = findViewById(R.id.edt_tennl);
        edtNhaCC = findViewById(R.id.edt_ncc);
        edtLoaiNL = findViewById(R.id.edt_loai);
        edtDonGia = findViewById(R.id.edt_dongia);
        edtSoLuong = findViewById(R.id.edt_soluong);
        edtDonVi = findViewById(R.id.edt_donvi);
        imgHinhAnh = findViewById(R.id.img_nguyenlieu);
        btnLuu = findViewById(R.id.btn_luu);
        btnHuy = findViewById(R.id.btn_huy);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALERY) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinhAnh.setImageBitmap(bitmap);
                //giảm dung lượng
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.2), (int) (bitmap.getHeight() * 0.2), true);
                byte[] anh = Utils.getBytes(resize);
                nguyenLieu.setHinhAnh(anh);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}