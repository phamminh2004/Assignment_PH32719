package com.minhpt.assignment_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TrangChu extends AppCompatActivity {
    TextView txt_phongban;
    TextView txt_nhanvien;
    TextView txt_thoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        txt_phongban = findViewById(R.id.txt_phongban);
        txt_nhanvien = findViewById(R.id.txt_nhanvien);
        txt_thoat = findViewById(R.id.txt_thoat);
        txt_phongban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PhongBan.class));
            }
        });
        txt_nhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NhanVien.class));
            }
        });
        txt_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DangNhap.class));
            }
        });
    }
}