package com.example.evaluacion3app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EliminarEquipo extends AppCompatActivity {
    EditText edtEquipoId;
    TextView tvEquipoMarca, tvEquipoModelo, tvEquipoRam, tvEquipoSistema, tvEquipoRut, tvEquipoEstado, tvEquipoRequerimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_equipo);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        edtEquipoId = findViewById(R.id.eliminarEquipo_edtId);
        tvEquipoMarca = findViewById(R.id.eliminarEquipo_tvMarca);
        tvEquipoModelo = findViewById(R.id.eliminarEquipo_tvModelo);
        tvEquipoRam = findViewById(R.id.eliminarEquipo_tvRam);
        tvEquipoSistema = findViewById(R.id.eliminarEquipo_tvSistema);
        tvEquipoRut = findViewById(R.id.eliminarEquipo_tvRut);
        tvEquipoEstado = findViewById(R.id.eliminarEquipo_tvEstado);
        tvEquipoRequerimiento = findViewById(R.id.eliminarEquipo_tvRequerimiento);
    }

    public void buscarEquipo(View view) {
        gestorBaseDeDatos gestor = new gestorBaseDeDatos(this, "ClinicaPc", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String id = edtEquipoId.getText().toString();

        Cursor datos = db.rawQuery("SELECT marca, modelo, ram, sistema, rut_propietario, estado, requerimiento " +
                "FROM equipos WHERE id = " + id, null);

        if (datos.moveToFirst()) {
            tvEquipoMarca.setText("Marca: " + datos.getString(0));
            tvEquipoModelo.setText("Modelo: " + datos.getString(1));
            tvEquipoRam.setText("RAM: " + datos.getString(2));
            tvEquipoSistema.setText("Sistema: " + datos.getString(3));
            tvEquipoRut.setText("RUT del dueño: " + datos.getString(4));
            tvEquipoEstado.setText("Estado: " + datos.getString(5));
            tvEquipoRequerimiento.setText("Requerimiento: " + datos.getString(6));

            db.close();
        } else {
            Toast.makeText(this, "No existe un equipo asociado a ese ID.", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    public void eliminarEquipo(View view) {
        gestorBaseDeDatos gestor = new gestorBaseDeDatos(this, "ClinicaPc", null, 1);
        SQLiteDatabase db = gestor.getReadableDatabase();

        String id = edtEquipoId.getText().toString();

        if (!id.isEmpty()) {
            db.delete( "equipos", "id=" + id, null);
            Toast.makeText(this, "Equipo eliminado.", Toast.LENGTH_SHORT).show();

            edtEquipoId.setText("");
            tvEquipoMarca.setText("");
            tvEquipoModelo.setText("");
            tvEquipoRam.setText("");
            tvEquipoSistema.setText("");
            tvEquipoRut.setText("");
            tvEquipoEstado.setText("");
            tvEquipoRequerimiento.setText("");
        } else {
            Toast.makeText(this, "Ingrese el ID del equipo.", Toast.LENGTH_SHORT).show();
        }

    }
}