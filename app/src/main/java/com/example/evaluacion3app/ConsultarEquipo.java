package com.example.evaluacion3app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultarEquipo extends AppCompatActivity {
    EditText edtEquipoId;
    TextView tvEstado, tvComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_equipo);

        edtEquipoId = findViewById(R.id.consultarEquipo_edtId);
        tvEstado = findViewById(R.id.consultarEquipo_tvEstado);
        tvComentario = findViewById(R.id.consultarEquipo_tvComentario);
    }

    public void consultarEquipoUsuario(View view) {
        gestorBaseDeDatos gestor = new gestorBaseDeDatos(this, "ClinicaPc", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String id = edtEquipoId.getText().toString();

        Cursor datos = db.rawQuery("SELECT estado, comentario FROM equipos WHERE id = " + id, null);

        if (!id.isEmpty()) {
            if (datos.moveToFirst()) {
                tvEstado.setText("Estado: " + datos.getString(0));
                tvComentario.setText("Comentarios del técnico: " + datos.getString(1));

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
}