package com.example.appgimnasio.repositories.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appgimnasio.repositories.local.ControlPorcino.PersistenceControlPorcino;
import com.example.appgimnasio.repositories.local.PartoPorcino.PersistencePartoPorcino;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                                int version){
        super(context,name,factory,version);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(PersistenceControlPorcino.ControlPorcinoEntry.CREAR_TABLE_CONTROL_PORCINO);
        db.execSQL(PersistencePartoPorcino.PartoPorcinoEntry.CREAR_TABLE_PARTO_PROCINO);
    }

    public void onUpgrade(SQLiteDatabase db, int prevVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+PersistenceControlPorcino.ControlPorcinoEntry.TABLE_CONTROL_PORCINO);
        db.execSQL("DROP TABLE IF EXISTS "+PersistencePartoPorcino.PartoPorcinoEntry.TABLE_PARTO_PORCINO);
        onCreate(db);
    }
}
