package com.vikosi.Bring_bagg;

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
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.vikosi.Bring_bagg.Models.User;

public class RegistrActivity1 extends AppCompatActivity {
    Button LoaderRegistr, ClientRegistr;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    ConstraintLayout getcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr1);
        LoaderRegistr=findViewById(R.id.LoaderRegistr);
        ClientRegistr=findViewById(R.id.ClientRegistr);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users"); //таблица

        getcode = findViewById(R.id.GetCode);

        ClientRegistr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCRegisterWindow();

            }
        });
        LoaderRegistr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLRegisterWindow();

            }
        });

    }
//Регистрация клиента
    private void showCRegisterWindow() {
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
        dialog1.setTitle("Регистрация клиента");
        dialog1.setMessage("Введите данные для регистрации в приложении");

        LayoutInflater inflater1 = LayoutInflater.from(this);
        View register_window_client = inflater1.inflate(R.layout.activity_profile_create_client, null);
        dialog1.setView(register_window_client);

        final MaterialEditText name = register_window_client.findViewById(R.id.NameField);
        final MaterialEditText email = register_window_client.findViewById(R.id.EmailField);
        final MaterialEditText phone = register_window_client.findViewById(R.id.PhoneField);
        final MaterialEditText pass = register_window_client.findViewById(R.id.PasswordField);
        final MaterialEditText birth = register_window_client.findViewById(R.id.BirthdayField);

        dialog1.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog1.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(getcode, "Введите почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(getcode, "Введите фамилию и имя", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if((pass.getText().toString().length()<6)){
                    Snackbar.make(getcode, "Введите пароль, в котором более 6 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(getcode, "Введите номер телефона", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(birth.getText().toString())){
                    Snackbar.make(getcode, "Введите дату рождения", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                //Успешное добавление
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(email.getText().toString());
                                user.setName(name.getText().toString());
                                user.setPhone(phone.getText().toString());
                                user.setPass(pass.getText().toString());
                                user.setBirth(birth.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                               Snackbar.make(getcode, "Пользователь успешно добавлен", Snackbar.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        });
            }
        });
        dialog1.show();
    }
//Регистрация грузчика
    private void showLRegisterWindow() {
        AlertDialog.Builder dialog2 = new AlertDialog.Builder(this);
        dialog2.setTitle("Регистрация грузоперевозчика");
        dialog2.setMessage("Введите данные для регистрации в приложении");

        LayoutInflater inflater2 = LayoutInflater.from(this);
        View register_window_loader = inflater2.inflate(R.layout.activity_profile_create_loader, null);
        dialog2.setView(register_window_loader);

        final MaterialEditText name = register_window_loader.findViewById(R.id.NameField);
        final MaterialEditText email = register_window_loader.findViewById(R.id.EmailField);
        final MaterialEditText phone = register_window_loader.findViewById(R.id.PhoneField);
        final MaterialEditText pass = register_window_loader.findViewById(R.id.PasswordField);
        final MaterialEditText birth = register_window_loader.findViewById(R.id.BirthdayField);
        final MaterialEditText date = register_window_loader.findViewById(R.id.PravaDateField);
        final MaterialEditText serial = register_window_loader.findViewById(R.id.PravaSerialField);
        final MaterialEditText vin = register_window_loader.findViewById(R.id.VinField);
        final MaterialEditText passport = register_window_loader.findViewById(R.id.PassportField);

        dialog2.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog2.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(getcode, "Введите почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(getcode, "Введите фамилию и имя", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if((pass.getText().toString().length()<6)){
                    Snackbar.make(getcode, "Введите пароль, в котором более 6 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(getcode, "Введите номер телефона", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(birth.getText().toString())){
                    Snackbar.make(getcode, "Введите дату рождения", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(vin.getText().toString())){
                    Snackbar.make(getcode, "Введите VIN", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(date.getText().toString())){
                    Snackbar.make(getcode, "Введите дату выдачи и окончания ВУ (напр. 01.01.1901-01.01.1911)", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(serial.getText().toString())){
                    Snackbar.make(getcode, "Введите серию и номер ВУ без пробелов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passport.getText().toString())){
                    Snackbar.make(getcode, "Введите серию и номер паспорта без пробелов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                //Успешное добавление
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(email.getText().toString());
                                user.setName(name.getText().toString());
                                user.setPhone(phone.getText().toString());
                                user.setPass(pass.getText().toString());
                                user.setBirth(birth.getText().toString());
                                user.setBirth(vin.getText().toString());
                                user.setBirth(serial.getText().toString());
                                user.setBirth(passport.getText().toString());
                                user.setBirth(date.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(getcode, "Пользователь успешно добавлен", Snackbar.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        });
            }
        });
        dialog2.show();
    }


}