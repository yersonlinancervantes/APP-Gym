package com.example.appgimnasio.repositories.local.PartoPorcino;

public class PersistencePartoPorcino {
    public static abstract class PartoPorcinoEntry{

        public static final String TABLE_PARTO_PORCINO= "PartoPorcino";
        public static final String CAMPO_ID = "id";
        public static final String CAMPO_PORCINO_ID = "porcinoId";
        public static final String CAMPO_FECHA_PARTO = "fechaParto";
        public static final String CAMPO_LECHONES_VIVOS_MACHOS = "lechonesVivosMachos";
        public static final String CAMPO_LECHONES_VIVOS_HEMBRAS = "lechonesVivosHembras";
        public static final String CAMPO_LECHONES_MUERTOS_MACHOS = "lechonesMuertosMachos";
        public static final String CAMPO_LECHONES_MUERTOS_HEMBRAS = "lechonesMuertosHembras";
        public static final String CAMPO_PROMEDIO_PESO = "promedioPeso";

        public static final String CREAR_TABLE_PARTO_PROCINO= "CREATE TABLE "+TABLE_PARTO_PORCINO+" ("+
                CAMPO_ID+" " + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ""+CAMPO_PORCINO_ID+" INTEGER NOT NULL, " +
                ""+CAMPO_FECHA_PARTO+" TEXT NOT NULL, " +
                ""+CAMPO_LECHONES_VIVOS_MACHOS+" INTEGER NOT NULL, " +
                ""+CAMPO_LECHONES_VIVOS_HEMBRAS+" INTEGER NOT NULL, " +
                ""+CAMPO_LECHONES_MUERTOS_MACHOS+" INTEGER NOT NULL, "+
                ""+CAMPO_LECHONES_MUERTOS_HEMBRAS+" INTEGER NOT NULL, "+
                ""+CAMPO_PROMEDIO_PESO+" REAL NOT NULL"+
                ")";
    }
}
