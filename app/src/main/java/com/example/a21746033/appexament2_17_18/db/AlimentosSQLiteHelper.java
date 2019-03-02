package com.example.a21746033.appexament2_17_18.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.a21746033.appexament2_17_18.model.Alimento;

import java.util.ArrayList;

public class AlimentosSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AlimentosDB";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_ALIMENTOS = "CREATE TABLE "+AlimentosContract.AlimentosEntry.TABLE_NAME+
            "("+
            AlimentosContract.AlimentosEntry.COLUMN_ID+", "+
            AlimentosContract.AlimentosEntry.COLUMN_NOMBRE+", "+
            AlimentosContract.AlimentosEntry.COLUMN_TIPO+", "+
            AlimentosContract.AlimentosEntry.COLUMN_ORIGEN+", "+
            AlimentosContract.AlimentosEntry.COLUMN_NUTRIENTES+", "+
            AlimentosContract.AlimentosEntry.COLUMN_FUNCION +
            ")";

    private ArrayList<Alimento> listaAlimento = new ArrayList<>();

    public AlimentosSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALIMENTOS);
        cargarDatos();
        for(Alimento alimento : listaAlimento){
            db.execSQL("INSERT INTO "+AlimentosContract.AlimentosEntry.TABLE_NAME +
                    " ("+
                    AlimentosContract.AlimentosEntry.COLUMN_NOMBRE +", "+
                    AlimentosContract.AlimentosEntry.COLUMN_TIPO +", "+
                    AlimentosContract.AlimentosEntry.COLUMN_ORIGEN +", "+
                    AlimentosContract.AlimentosEntry.COLUMN_NUTRIENTES +", "+
                    AlimentosContract.AlimentosEntry.COLUMN_FUNCION +
                    ") VALUES ('"+alimento.getNombre()+"','"+alimento.getTipo()+"','"+alimento.getOrigen()+"','"+alimento.getNutrientes()+"','"+alimento.getFuncion()+"')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+AlimentosContract.AlimentosEntry.TABLE_NAME);
        onCreate(db);
    }

    public void cargarDatos(){
        listaAlimento.add(new Alimento("arroz","Cereales","Vegetal","Carbohidratos","Energética"));
        listaAlimento.add(new Alimento("pasta","Cereales","Vegetal","Carbohidratos","Energética"));
        listaAlimento.add(new Alimento("pan","Cereales","Vegetal","Carbohidratos","Energética"));
        listaAlimento.add(new Alimento("leche","Lácteos","Animal","Proteínas animales","Plástica"));
        listaAlimento.add(new Alimento("queso","Lácteos","Animal","Proteínas animales","Plástica"));
        listaAlimento.add(new Alimento("yogurt","Lácteos","Animal","Proteínas animales","Plástica"));
        listaAlimento.add(new Alimento("huevos","Huevos","Animal","Proteínas animales","Plástica"));
        listaAlimento.add(new Alimento("azúcar","Azúcares","Vegetal","Carbohidratos","Energética"));
        listaAlimento.add(new Alimento("chocolate","Azúcares","Vegetal","Carbohidratos","Energética"));
        listaAlimento.add(new Alimento("aceite oliva","Aceites","Vegetal","Lípidos","Energética"));
        listaAlimento.add(new Alimento("berenjena","Verduras y Hortaliza","Vegetal","Vitaminas","Reguladora"));
        listaAlimento.add(new Alimento("calabacín","Verduras y Hortalizas","Vegetal","Vitaminas","Reguladora"));
        listaAlimento.add(new Alimento("tomate","Verduras y Hortalizas","Vegetal","Vitaminas","Reguladora"));
        listaAlimento.add(new Alimento("zanahoria","Verduras y Hortalizas","Vegetal","Vitaminas","Reguladora"));
        listaAlimento.add(new Alimento("patata","Verduras y Hortalizas","Vegetal","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        listaAlimento.add(new Alimento("garbanzos","Legumbres","Vegetal","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        listaAlimento.add(new Alimento("lentejas","Legumbre","Vegetal","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        listaAlimento.add(new Alimento("naranja","Frutas","Vegetal","Vitaminas","Reguladora"));
        listaAlimento.add(new Alimento("plátano","Frutas","Vegeta","Vitaminas","Reguladora"));
        listaAlimento.add(new Alimento("manzana","Frutas","Vegetal","Vitaminas","Reguladora"));
        listaAlimento.add(new Alimento("almendra","Frutos secos","Vegeta","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        listaAlimento.add(new Alimento("nuez","Frutos secos","Vegetal","Vitaminas, Proteínas Vegetales y Lípidos","Reguladora, Plástica y Energética"));
        listaAlimento.add(new Alimento("jamón","Carne","Animal","Proteínas animales","Plástica"));
        listaAlimento.add(new Alimento("conejo","Carne","Animal","Proteínas animales","Plástica"));
        listaAlimento.add(new Alimento("pollo","Carne","Animal","Proteínas animales","Plástica"));
        listaAlimento.add(new Alimento("bacalao","Pescado","Animal","Proteínas animales","Plástica"));
        listaAlimento.add(new Alimento("merluza","Pescado","Animal","Proteínas animales","Plástica"));
        listaAlimento.add(new Alimento("salmón","Pescado","Animal","Proteínas animales","Plástica"));
    }

    public ArrayList<Alimento> getListaAlimento() {
        return listaAlimento;
    }
}
