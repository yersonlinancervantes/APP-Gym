package com.example.appgimnasio.repositories.local.ControlPorcino;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appgimnasio.entity.ControlPorcino;
import com.example.appgimnasio.repositories.local.ConexionSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class ControlPorcinoSQLiteManager {

    private SQLiteDatabase db;
    ConexionSQLiteHelper conn;
    Context context;

    public ControlPorcinoSQLiteManager(Context context){
        this.context = context;
        conn = new ConexionSQLiteHelper(context,"bd_qr",null,
                1);
    }

    public void actualizarControlPorcino(ControlPorcino controlPorcino){

        ContentValues values = new ContentValues();
        values.put(PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_PESO,controlPorcino.getPeso());
        values.put(PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_FECHA_VACUNACION,controlPorcino.getFechaVacunacion());
        values.put(PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_DOSIS,controlPorcino.getDosis());
        values.put(PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_OBSERVACION,controlPorcino.getObservacion());

        db.update(PersistenceControlPorcino.ControlPorcinoEntry.TABLE_CONTROL_PORCINO,values,
                PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_ID+" = ?",
                new String[]{String.valueOf(controlPorcino.getId())});

    }

    public void registerSegimiento(ControlPorcino controlPorcino){

        db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_PORCINO_ID,controlPorcino.getPorcinoId());
        values.put(PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_PESO,controlPorcino.getPeso());
        values.put(PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_FECHA_VACUNACION,controlPorcino.getFechaVacunacion());
        values.put(PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_DOSIS,controlPorcino.getDosis());
        values.put(PersistenceControlPorcino.ControlPorcinoEntry.CAMPO_OBSERVACION,controlPorcino.getObservacion());

        db.insert(PersistenceControlPorcino.ControlPorcinoEntry.TABLE_CONTROL_PORCINO,null,
                values);
    }

    public ControlPorcino getControlPorcinoById(int pId){

        ControlPorcino controlPorcino = null;

        db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery(" " +
                "SELECT id, " +
                "porcinoId, " +
                "peso, " +
                "fechaVacunacion, " +
                "dosis, " +
                "observacion " +
                "FROM ControlPorcino WHERE id="+pId,null);

        while(cursor.moveToNext()){

            controlPorcino = new ControlPorcino(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    Double.parseDouble(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
        }

        return controlPorcino;
    }

    public List<ControlPorcino> getControlPorcinoAll(int pPorcinoId){

        List<ControlPorcino> listControlPorcino = new ArrayList<>();
        ControlPorcino controlPorcino=null;

        db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery(" " +
                "SELECT id, " +
                "porcinoId, " +
                "peso, " +
                "fechaVacunacion, " +
                "dosis, " +
                "observacion " +
                "FROM ControlPorcino WHERE porcinoId="+pPorcinoId,null);

        while(cursor.moveToNext()){

            controlPorcino = new ControlPorcino(
                                                Integer.parseInt(cursor.getString(0)),
                                                Integer.parseInt(cursor.getString(1)),
                                                Double.parseDouble(cursor.getString(2)),
                                                cursor.getString(3),
                                                cursor.getString(4),
                                                cursor.getString(5)
                                                );

            listControlPorcino.add(controlPorcino);
        }

        return listControlPorcino;
    }

}
