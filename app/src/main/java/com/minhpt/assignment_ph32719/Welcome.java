package com.minhpt.assignment_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        setContentView(R.layout.welcome);
        Thread time = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {

                } finally {
                    startActivity(new Intent(Welcome.this, DangNhap.class));
                }
            }
        };
        time.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

