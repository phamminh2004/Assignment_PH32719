package com.minhpt.assignment_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DangKy extends AppCompatActivity {
    public static String KEY_USERNAME = "user_name";
    public static String KEY_PASSWORD = "password";
    Context context = this;

    public void writeUser(Context context, String fileName, User user) {
        List<User> list = new ArrayList<>();
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            list.add(user);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        Button btnRegister = findViewById(R.id.btn_register);
        Button btnBack = findViewById(R.id.btn_back);
        EditText edt_username = findViewById(R.id.edt_username);
        EditText edt_password = findViewById(R.id.edt_password);
        EditText edt_retype_password = findViewById(R.id.edt_retype_password);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                String retype_password = edt_retype_password.getText().toString();

                if (username == null || username.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Cần nhập Username!", Toast.LENGTH_SHORT).show();
                } else if (password == null || password.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Cần nhập Password!", Toast.LENGTH_SHORT).show();
                } else if (retype_password == null || retype_password.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Cần nhập lại Password!", Toast.LENGTH_SHORT).show();
                } else if (!retype_password.equals(password)) {
                    Toast.makeText(getApplicationContext(), "Password nhập lại sai", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DangNhap.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_USERNAME, username);
                    bundle.putString(KEY_PASSWORD, password);
                    intent.putExtras(bundle);
                    writeUser(context, "account.txt", new User(edt_username.getText().toString(), edt_password.getText().toString()));
                    startActivity(intent);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
