package com.minhpt.assignment_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangNhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
        EditText edtUsername = findViewById(R.id.edt_username);
        EditText edtPassword = findViewById(R.id.edt_password);

        String sUserName = getIntent().getStringExtra(DangKy.KEY_USERNAME);
        String sPassword = getIntent().getStringExtra(DangKy.KEY_PASSWORD);

        edtUsername.setText(sUserName);
        edtPassword.setText(sPassword);

        int number = getIntent().getIntExtra("number", 0);
        Toast.makeText(this, number + "", Toast.LENGTH_SHORT).show();

        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUserName = edtUsername.getText().toString();
                String sPassword = edtPassword.getText().toString();

                if (sUserName == null || sUserName.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Cần nhập Username!", Toast.LENGTH_SHORT).show();
                } else if (sPassword == null || sPassword.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Cần nhập Password!", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(DangNhap.this, TrangChu.class));
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "Vào onPause DangNhap", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(), "Vào onStop DangNhap", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "Vào onResume DangNhap", Toast.LENGTH_SHORT).show();
    }
}