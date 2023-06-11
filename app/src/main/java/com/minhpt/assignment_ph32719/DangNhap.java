package com.minhpt.assignment_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class DangNhap extends AppCompatActivity {
    Context context = this;
    CheckBox chk_remember;
    EditText edt_username;
    EditText edt_password;
    Button btn_register;

    public List<User> readUser(Context context, String fileName) {
        List<User> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (List<User>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void remember(String user, String pass, boolean chk_remember) {
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", user);
        editor.putString("pass", pass);
        editor.putBoolean("chk_remember", chk_remember);
        editor.apply();
    }

    public void checkRemember() {
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        String pass = sharedPreferences.getString("pass", "");
        boolean chk_remember1 = sharedPreferences.getBoolean("chk_remember", false);
        chk_remember.setChecked(chk_remember1);
        if (chk_remember.isChecked()) {
            edt_username.setText(user);
            edt_password.setText(pass);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        chk_remember = findViewById(R.id.chk_remember);
        btn_register = findViewById(R.id.btn_register);

        checkRemember();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhap.this, DangKy.class));
            }
        });
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> list = new ArrayList<>();
                list = readUser(context, "account.txt");

                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();

                if (username == null || username.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Cần nhập Username!", Toast.LENGTH_SHORT).show();
                } else if (password == null || password.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Cần nhập Password!", Toast.LENGTH_SHORT).show();
                } else if (!username.equals(list.get(0).getUsername()) && !password.equals(list.get(0).getPassword())) {
                    Toast.makeText(DangNhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    remember(list.get(0).getUsername(), list.get(0).getPassword(), true);
                    startActivity(new Intent(DangNhap.this, TrangChu.class));
                    Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}