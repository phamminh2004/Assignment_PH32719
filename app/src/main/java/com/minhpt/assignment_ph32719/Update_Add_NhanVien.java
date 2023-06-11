package com.minhpt.assignment_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update_Add_NhanVien extends AppCompatActivity {
    EditText edt_manv;
    EditText edt_tennv;
    EditText edt_pbnv;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        edt_manv = findViewById(R.id.edt_manv);
        edt_tennv = findViewById(R.id.edt_tennv);
        edt_pbnv = findViewById(R.id.edt_pbnv);
        btn_submit = findViewById(R.id.btn_submit);

        NhanVienModel nhanVienModel = (NhanVienModel) getIntent().getSerializableExtra("nhanvien");
        if (nhanVienModel != null) {
            edt_manv.setText(nhanVienModel.getMaNV());
            edt_tennv.setText(nhanVienModel.getHoten());
            edt_pbnv.setText(nhanVienModel.getPb());
        }
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String ma = edt_manv.getText().toString();
                String ten = edt_tennv.getText().toString();
                String pb = edt_pbnv.getText().toString();
                intent.putExtra("data", new NhanVienModel(ma, ten, pb));
                setResult(1, intent);
                finish();
            }
        });
    }
}