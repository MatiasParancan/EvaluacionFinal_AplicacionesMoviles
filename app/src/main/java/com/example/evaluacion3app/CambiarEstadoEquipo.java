package com.example.evaluacion3app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CambiarEstadoEquipo extends AppCompatActivity {
    Spinner spEstado;
    EditText edtComentario;
    String equipoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_estado_equipo);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        spEstado = findViewById(R.id.cambiarEstado_spEstado);
        edtComentario = findViewById(R.id.cambiarEstado_edtComentario);

        Bundle bundle = getIntent().getExtras();
        equipoId = bundle.getString("equipoId");
    }

    public void editarEstado(View view) {
        gestorBaseDeDatos gestor = new gestorBaseDeDatos(this, "ClinicaPc", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String comentario = edtComentario.getText().toString();
        String estado = spEstado.getSelectedItem().toString();
        String id = equipoId;

        ContentValues fila = new ContentValues();

        fila.put("estado", estado);
        fila.put("comentario", comentario);

        if (!comentario.isEmpty()) {
            int filas = db.update("equipos", fila, "id=" + id,null);

            if (filas == 1) {
                Toast.makeText(this, "Estado del equipo actualizado con éxito.", Toast.LENGTH_SHORT).show();
                db.close();

                edtComentario.setText("");
            } else {
                Toast.makeText(this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
                db.close();
            }
        } else {
            Toast.makeText(this, "Rellene todos los campos.", Toast.LENGTH_SHORT).show();
        }
    }
}