package com.example.evaluacion3app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleEquipo extends AppCompatActivity {
    EditText edtEquipoId;
    TextView tvEquipoMarca, tvEquipoModelo, tvEquipoRam, tvEquipoSistema, tvEquipoRut, tvEquipoEstado, tvEquipoRequerimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_equipo);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        edtEquipoId = findViewById(R.id.detalle_IdEquipo);
        tvEquipoMarca = findViewById(R.id.detalle_marca);
        tvEquipoModelo = findViewById(R.id.detalle_modelo);
        tvEquipoRam = findViewById(R.id.detalle_ram);
        tvEquipoSistema = findViewById(R.id.detalle_sistema);
        tvEquipoRut = findViewById(R.id.detalle_rut);
        tvEquipoEstado = findViewById(R.id.detalle_estado);
        tvEquipoRequerimiento = findViewById(R.id.detalle_requerimiento);
    }

    public void consultarEquipo(View view) {
        gestorBaseDeDatos gestor = new gestorBaseDeDatos(this, "ClinicaPc", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String id = edtEquipoId.getText().toString();

        Cursor datos = db.rawQuery("SELECT marca, modelo, ram, sistema, rut_propietario, estado, requerimiento " +
                "FROM equipos WHERE id = " + id, null);

        if(!id.isEmpty()) {
            if (datos.moveToFirst()) {
                tvEquipoMarca.setText("Marca: " + datos.getString(0));
                tvEquipoModelo.setText("Modelo: " + datos.getString(1));
                tvEquipoRam.setText("RAM: " + datos.getString(2));
                tvEquipoSistema.setText("Sistema Operativo: " + datos.getString(3));
                tvEquipoRut.setText("RUT del dueño: " + datos.getString(4));
                tvEquipoEstado.setText("Estado: " + datos.getString(5));
                tvEquipoRequerimiento.setText("Requerimiento: " + datos.getString(6));

                edtEquipoId.setText("");
                db.close();
            } else {
                Toast.makeText(this, "No existe un equipo asociado a ese ID.", Toast.LENGTH_SHORT).show();
                db.close();
            }
        } else {
            Toast.makeText(this, "Ingrese el ID del equipo.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToCambiarEstado(View view) {
        String id = edtEquipoId.getText().toString();

        if (!id.isEmpty()) {
        Intent cambiarEstado = new Intent(this, CambiarEstadoEquipo.class);
        cambiarEstado.putExtra("equipoId", id);
        startActivity(cambiarEstado);
        } else {
            Toast.makeText(this, "Ingrese el ID del equipo.", Toast.LENGTH_SHORT).show();
        }
    }
}