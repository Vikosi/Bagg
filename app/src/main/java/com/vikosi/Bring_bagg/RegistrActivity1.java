package com.vikosi.Bring_bagg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistrActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr1);
    }

    public void onClickLoader(View view) {
        Intent intent = new Intent(RegistrActivity1.this, ProfileCreateActivityLoader.class);
        startActivity(intent);
    }

    public void onClickClient(View view) {
        Intent intent = new Intent(RegistrActivity1.this, CreateClientActivity.class);
        startActivity(intent);
    }
}