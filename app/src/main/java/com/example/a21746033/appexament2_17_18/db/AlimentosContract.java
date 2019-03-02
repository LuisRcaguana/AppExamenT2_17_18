package com.example.a21746033.appexament2_17_18.db;

import android.provider.BaseColumns;

public class AlimentosContract {

    public static abstract class AlimentosEntry implements BaseColumns{

        static final String TABLE_NAME = "ALIMENTOS";

        static final String COLUMN_ID = BaseColumns._ID;
        static final String COLUMN_NOMBRE = "NOMBRE";
        static final String COLUMN_TIPO = "TIPO";
        static final String COLUMN_ORIGEN = "ORIGEN";
        static final String COLUMN_NUTRIENTES = "NUTRIENTES";
        static final String COLUMN_FUNCION = "FUNCION";
    }
}
