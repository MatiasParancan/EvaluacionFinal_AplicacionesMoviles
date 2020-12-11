package com.example.evaluacion3app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IngresarEquipo extends AppCompatActivity {
    EditText equipoId, equipoMarca, equipoModelo, equipoRAM, equipoSO, equipoRUT, equipoRequerimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_equipo);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        equipoId = findViewById(R.id.ingresarEquipo_edtId);
        equipoMarca = findViewById(R.id.ingresarEquipo_edtMarca);
        equipoModelo = findViewById(R.id.ingresarEquipo_edtModelo);
        equipoRAM = findViewById(R.id.ingresarEquipo_edtRAM);
        equipoSO = findViewById(R.id.ingresarEquipo_edtSO);
        equipoRUT = findViewById(R.id.ingresarEquipo_edtRUT);
        equipoRequerimiento = findViewById(R.id.ingresarEquipo_edtRequerimiento);
    }

    public void ingresarEquipo(View view) {
        gestorBaseDeDatos gestor = new gestorBaseDeDatos(this, "ClinicaPc", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String id = equipoId.getText().toString();
        String marca = equipoMarca.getText().toString();
        String modelo = equipoModelo.getText().toString();
        String ram = equipoRAM.getText().toString();
        String sistema = equipoSO.getText().toString();
        String rut = equipoRUT.getText().toString();
        String estado = "Ingresado";
        String requerimiento = equipoRequerimiento.getText().toString();
        String comentario = "";

        if (!id.isEmpty() && !marca.isEmpty() && !modelo.isEmpty() && !ram.isEmpty() && !sistema.isEmpty() && !rut.isEmpty() &&
        !requerimiento.isEmpty()) {
            ContentValues fila = new ContentValues();
            fila.put("id", id);
            fila.put("marca", marca);
            fila.put("modelo", modelo);
            fila.put("ram", ram);
            fila.put("sistema", sistema);
            fila.put("rut_propietario", rut);
            fila.put("estado", estado);
            fila.put("requerimiento", requerimiento);
            fila.put("comentario", comentario);

            db.insert("equipos",null, fila);
            Toast.makeText(this, "Equipo ingresado con éxito.", Toast.LENGTH_SHORT).show();
            db.close();

            equipoId.setText("");
            equipoMarca.setText("");
            equipoModelo.setText("");
            equipoRAM.setText("");
            equipoSO.setText("");
            equipoRUT.setText("");
            equipoRequerimiento.setText("");

        } else {
            Toast.makeText(this, "Rellene todos los campos.", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
}