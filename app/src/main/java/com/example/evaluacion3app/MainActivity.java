package com.example.evaluacion3app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void goToConsultarEquipo(View view) {
        Intent consultarEquipo = new Intent(this, ConsultarEquipo.class);
        startActivity(consultarEquipo);
    }

    public void goToIngresarEquipo(View view) {
        Intent ingresarEquipo = new Intent(this, IngresarEquipo.class);
        startActivity(ingresarEquipo);
    }

    public void goToDetalleEquipo(View view) {
        Intent detalleEquipo = new Intent(this, DetalleEquipo.class);
        startActivity(detalleEquipo);
    }

    public void goToLogin(View view) {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
}