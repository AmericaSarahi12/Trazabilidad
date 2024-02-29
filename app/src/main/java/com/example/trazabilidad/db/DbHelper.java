package com.example.trazabilidad.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

import java.sql.Connection;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Trazabilidad.db";
    public static final String TABLE_CALALOGOS = "CATALOGOS";
    public static final String TABLE_TRAZA_TXT ="TRAZA_TXT";

    public static final String TABLE_XML_PROV_DETALLE_CAJA="XML_PROV_DETALLE_CAJA";

    public static Connection objConexion = null;


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s(ID_Cat INTEGER PRIMARY KEY AUTOINCREMENT,Provedor text not null,ID_Pro Integer  ,Desc_prov integer,ID_SAP INTEGER,Desc_SAP Integer,Usuarios_Registrados Integer,Fecha_Registro date,Comentarios)", TABLE_CALALOGOS)
        );
        db.execSQL("CREATE TABLE "+TABLE_TRAZA_TXT+"("+
                "ID_Prov INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Provedor text not null,"+"Pedido text not null,"+
                "Codigo_QR TEXT NOT NULL,"+"Codigo_Traza INTEGER,"+"fecha date,"+"xml text not null)"
        );
        db.execSQL("CREATE TABLE "+TABLE_XML_PROV_DETALLE_CAJA +"("+
                "ID_Codigo_Caja INTEGER PRIMARY KEY AUTOINCREMENT)"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_CALALOGOS);
        onCreate(db);
    }

}
