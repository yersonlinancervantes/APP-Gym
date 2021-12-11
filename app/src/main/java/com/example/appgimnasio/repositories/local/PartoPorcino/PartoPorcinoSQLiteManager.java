package com.example.appgimnasio.repositories.local.PartoPorcino;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appgimnasio.entity.PartoPorcino;
import com.example.appgimnasio.repositories.local.ConexionSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class PartoPorcinoSQLiteManager {

    private SQLiteDatabase db;
    ConexionSQLiteHelper conn;
    Context context;

    public PartoPorcinoSQLiteManager(Context context){
        this.context = context;
        conn = new ConexionSQLiteHelper(context,"bd_qr",null,
                1);
    }

    public void registrarPartoPorcino(PartoPorcino partoPorcino){

        db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_PORCINO_ID,partoPorcino.getIdPorcino());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_FECHA_PARTO,partoPorcino.getFechaParto());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_LECHONES_VIVOS_MACHOS,partoPorcino.getLechonesVivosMachos());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_LECHONES_VIVOS_HEMBRAS,partoPorcino.getLechonesVivosHembras());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_LECHONES_MUERTOS_MACHOS,partoPorcino.getLechonesMuertosMachos());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_LECHONES_MUERTOS_HEMBRAS,partoPorcino.getLechonesMuertosHembras());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_PROMEDIO_PESO,partoPorcino.getPromedioPeso());

        db.insert(PersistencePartoPorcino.PartoPorcinoEntry.TABLE_PARTO_PORCINO,null,
                values);
    }


    public void actualizarPartoPorcino(PartoPorcino partoPorcino){

        ContentValues values = new ContentValues();
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_FECHA_PARTO,partoPorcino.getFechaParto());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_LECHONES_VIVOS_MACHOS,partoPorcino.getLechonesVivosMachos());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_LECHONES_VIVOS_HEMBRAS,partoPorcino.getLechonesVivosHembras());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_LECHONES_MUERTOS_MACHOS,partoPorcino.getLechonesMuertosMachos());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_LECHONES_MUERTOS_HEMBRAS,partoPorcino.getLechonesMuertosHembras());
        values.put(PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_PROMEDIO_PESO,partoPorcino.getPromedioPeso());

        db.update(PersistencePartoPorcino.PartoPorcinoEntry.TABLE_PARTO_PORCINO,values,
                PersistencePartoPorcino.PartoPorcinoEntry.CAMPO_ID+" = ?",
                new String[]{String.valueOf(partoPorcino.getId())});
    }

    public PartoPorcino getPartoPorcinoById(int pId){

        PartoPorcino partoPorcino = null;

        db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery(" " +
                "SELECT id, " +
                "porcinoId, " +
                "fechaParto, " +
                "lechonesVivosMachos, " +
                "lechonesVivosHembras, " +
                "lechonesMuertosMachos, " +
                "lechonesMuertosHembras, " +
                "promedioPeso " +
                "FROM PartoPorcino WHERE id="+pId,null);

        while(cursor.moveToNext()){
            partoPorcino = new PartoPorcino(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5)),
                    Integer.parseInt(cursor.getString(6)),
                    Double.parseDouble(cursor.getString(7))
            );
        }

        return partoPorcino;
    }

    public List<PartoPorcino> getPartoPorcinoAll(int pPorcinoId){

        List<PartoPorcino> listPartoPorcino = new ArrayList<>();
        PartoPorcino partoPorcino=null;

        db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery(" " +
                "SELECT id, " +
                "porcinoId, " +
                "fechaParto, " +
                "lechonesVivosMachos, " +
                "lechonesVivosHembras, " +
                "lechonesMuertosMachos, " +
                "lechonesMuertosHembras, " +
                "promedioPeso " +
                "FROM PartoPorcino WHERE porcinoId="+pPorcinoId,null);

        while(cursor.moveToNext()){

            partoPorcino = new PartoPorcino(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5)),
                    Integer.parseInt(cursor.getString(6)),
                    Double.parseDouble(cursor.getString(7))
            );

            listPartoPorcino.add(partoPorcino);
        }
        return listPartoPorcino;
    }
}
