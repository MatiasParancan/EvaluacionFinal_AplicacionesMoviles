package com.example.evaluacion3app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class gestorBaseDeDatos extends SQLiteOpenHelper {

    String createTable_equipos = "CREATE TABLE equipos(id int primary key, marca text, modelo text, ram text, sistema text," +
            "rut_propietario text, estado text, requerimiento text, comentario text)";

    public gestorBaseDeDatos(@Nullable Context context, @Nullable String name,
                             @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable_equipos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
