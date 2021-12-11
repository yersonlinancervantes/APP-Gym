package com.example.appgimnasio.repositories.local.ControlPorcino;

public class PersistenceControlPorcino {
    public static abstract class ControlPorcinoEntry{

        public static final String TABLE_CONTROL_PORCINO= "ControlPorcino";
        public static final String CAMPO_ID = "id";
        public static final String CAMPO_PORCINO_ID = "porcinoId";
        public static final String CAMPO_PESO = "peso";
        public static final String CAMPO_FECHA_VACUNACION = "fechaVacunacion";
        public static final String CAMPO_DOSIS = "dosis";
        public static final String CAMPO_OBSERVACION = "observacion";

        public static final String CREAR_TABLE_CONTROL_PORCINO= "CREATE TABLE "+TABLE_CONTROL_PORCINO+" ("+
                CAMPO_ID+" " + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ""+CAMPO_PORCINO_ID+" INTEGER NOT NULL, " +
                ""+CAMPO_PESO+" REAL NOT NULL, " +
                ""+CAMPO_FECHA_VACUNACION+" TEXT NOT NULL, " +
                ""+CAMPO_DOSIS+" TEXT NOT NULL, " +
                ""+CAMPO_OBSERVACION+" TEXT NOT NULL"+
                ")";
    }
}
