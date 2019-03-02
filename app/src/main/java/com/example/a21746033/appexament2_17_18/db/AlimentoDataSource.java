package com.example.a21746033.appexament2_17_18.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a21746033.appexament2_17_18.model.Alimento;

public class AlimentoDataSource {

    private AlimentosSQLiteHelper aSQLh;

    public AlimentoDataSource(Context context){
        aSQLh = new AlimentosSQLiteHelper(context);
    }

    private SQLiteDatabase openReadable() {
        return aSQLh.getReadableDatabase();
    }
    private SQLiteDatabase openWriteable() {
        return aSQLh.getWritableDatabase();
    }
    private void close(SQLiteDatabase database) {
        database.close();
    }

    public long registrarAlimento(Alimento alimento) {
        SQLiteDatabase sdb = openWriteable();
        sdb.beginTransaction();

        ContentValues cv = new ContentValues();
        cv.put(AlimentosContract.AlimentosEntry.COLUMN_NOMBRE,alimento.getNombre());
        cv.put(AlimentosContract.AlimentosEntry.COLUMN_TIPO,alimento.getTipo());
        cv.put(AlimentosContract.AlimentosEntry.COLUMN_ORIGEN,alimento.getOrigen());
        cv.put(AlimentosContract.AlimentosEntry.COLUMN_NUTRIENTES,alimento.getNutrientes());
        cv.put(AlimentosContract.AlimentosEntry.COLUMN_FUNCION,alimento.getFuncion());

        long result = sdb.insert(AlimentosContract.AlimentosEntry.TABLE_NAME,null,cv);
        if(result != -1){
            sdb.setTransactionSuccessful();
        }

        sdb.endTransaction();
        close(sdb);

        return result;
    }

    public Alimento consultarAlimento(String nombre) {
        SQLiteDatabase sdb = openReadable();

        String sql = "SELECT "+AlimentosContract.AlimentosEntry.COLUMN_ID+", "+
                AlimentosContract.AlimentosEntry.COLUMN_NOMBRE+", "+
                AlimentosContract.AlimentosEntry.COLUMN_TIPO+", "+
                AlimentosContract.AlimentosEntry.COLUMN_ORIGEN+", "+
                AlimentosContract.AlimentosEntry.COLUMN_NUTRIENTES+", "+
                AlimentosContract.AlimentosEntry.COLUMN_FUNCION+
                " FROM "+AlimentosContract.AlimentosEntry.TABLE_NAME+
                " WHERE "+AlimentosContract.AlimentosEntry.COLUMN_NOMBRE+" = ?";

        String[] argumentos = {nombre};

        Cursor cursor = sdb.rawQuery(sql,argumentos);

        Alimento alimento = null;

        if(cursor.moveToFirst()){
            alimento = new Alimento(
                    cursor.getInt(cursor.getColumnIndex(AlimentosContract.AlimentosEntry.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(AlimentosContract.AlimentosEntry.COLUMN_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(AlimentosContract.AlimentosEntry.COLUMN_TIPO)),
                    cursor.getString(cursor.getColumnIndex(AlimentosContract.AlimentosEntry.COLUMN_ORIGEN)),
                    cursor.getString(cursor.getColumnIndex(AlimentosContract.AlimentosEntry.COLUMN_NUTRIENTES)),
                    cursor.getString(cursor.getColumnIndex(AlimentosContract.AlimentosEntry.COLUMN_FUNCION))
            );
        }

        cursor.close();
        close(sdb);
        return alimento;
    }

    public void eliminarAlimento(Alimento alimento) {
        SQLiteDatabase sdb = openWriteable();
        sdb.beginTransaction();

        String clausulaWhere = AlimentosContract.AlimentosEntry.COLUMN_ID + " = ?";
        String[] argumentos = {String.valueOf(alimento.getId())};
        sdb.delete(AlimentosContract.AlimentosEntry.TABLE_NAME,
                clausulaWhere, argumentos);

        sdb.setTransactionSuccessful();
        sdb.endTransaction();
        close(sdb);
    }
}
