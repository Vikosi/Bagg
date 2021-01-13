package com.vikosi.Bring_bagg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.vikosi.Bring_bagg.Models.User;

public class MainActivity extends AppCompatActivity {

    Button VhodButton;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    ConstraintLayout getcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VhodButton = findViewById(R.id.VhodButton);

        VhodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVhodWindow();

            }
        });
    }

    private void showVhodWindow(){
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
        dialog1.setTitle("Вход в приложение");
        dialog1.setMessage("Введите данные для входа");

        LayoutInflater inflater1 = LayoutInflater.from(this);
        View vhod_window = inflater1.inflate(R.layout.vhod_window, null);
        dialog1.setView(vhod_window);

        final MaterialEditText email = vhod_window.findViewById(R.id.EmailField);
        final MaterialEditText pass = vhod_window.findViewById(R.id.PasswordField);

        dialog1.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog1.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(getcode, "Введите почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if((pass.getText().toString().length()<6)){
                    Snackbar.make(getcode, "Введите пароль, в котором более 6 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(MainActivity.this, ProfilActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(getcode, "Ошибка авторизации" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });
        dialog1.show();
    }


    public void onClickRegistr(View view) {
        Intent intent = new Intent(MainActivity.this, RegistrActivity1.class);
        startActivity(intent);
    }


}