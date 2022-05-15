package com.example.quanlynguyenlieeu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynguyenlieeu.Model.NguyenLieu;
import com.example.quanlynguyenlieeu.Model.NguyenLieuDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnThem;
    AutoCompleteTextView edtTimKiem;
    ArrayAdapter<NguyenLieu> adapter;
    ListView lvNguyenLieu;
    List<NguyenLieu> nguyenLieuList = new ArrayList<>();
    NguyenLieuDAO nguyenLieuDAO;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nguyenLieuDAO = new NguyenLieuDAO(MainActivity.this);
        anhXaView();
        loadData();
        registerForContextMenu(lvNguyenLieu);
        Log.e("LIST", nguyenLieuDAO.getNguyenLieuList().size() + "");
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                intent.putExtra("flag", false);
                startActivity(intent);
                finish();
            }
        });
        lvNguyenLieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                view.showContextMenu();
            }
        });

    }

    private void anhXaView() {
        btnThem = findViewById(R.id.btn_them);
        edtTimKiem = findViewById(R.id.edt_timkiem);
        lvNguyenLieu = findViewById(R.id.lv_nguyenlieu);
        lvNguyenLieu.setAdapter(adapter);
        edtTimKiem.setAdapter(adapter);
    }

    private void loadData() {
        nguyenLieuList = nguyenLieuDAO.getNguyenLieuList();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, nguyenLieuList);
        lvNguyenLieu.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Tùy chọn");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sua:
                NguyenLieu nguyenLieu = nguyenLieuList.get(index);
                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                intent.putExtra("NguyenLieu", (Serializable) nguyenLieu);
                intent.putExtra("flag", true);
                startActivity(intent);
                finish();
                break;
            case R.id.xoa:
                showDialog();
                break;
        }
        return true;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Bạn có chắc xóa không?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NguyenLieu nguyenLieu = nguyenLieuList.get(index);
                nguyenLieuDAO.delNguyenLieu(nguyenLieu.getMaNL());
                loadData();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

    }
}