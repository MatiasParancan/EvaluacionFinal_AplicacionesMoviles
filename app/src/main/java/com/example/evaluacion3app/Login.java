package com.example.evaluacion3app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    String correoAdmin = "leicaman@inacap.cl";
    String passwordAdmin = "superleicaman";
    EditText login_edtCorreo, login_edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        login_edtCorreo = findViewById(R.id.login_edtCorreo);
        login_edtPassword = findViewById(R.id.login_edtPassword);
    }

    public void ingresar(View view) {
        String correo = login_edtCorreo.getText().toString();
        String password = login_edtPassword.getText().toString();

        if (!correo.isEmpty() && !password.isEmpty()) {
            if (correo.equals(correoAdmin) && password.equals(passwordAdmin)) {
                Intent eliminarActivity = new Intent(this, EliminarEquipo.class);
                startActivity(eliminarActivity);
            }
        } else {
            Toast.makeText(this, "Rellene todos los campos.", Toast.LENGTH_SHORT).show();
        }
    }
}