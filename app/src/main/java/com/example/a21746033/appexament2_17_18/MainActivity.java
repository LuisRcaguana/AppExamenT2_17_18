package com.example.a21746033.appexament2_17_18;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21746033.appexament2_17_18.db.AlimentoDataSource;
import com.example.a21746033.appexament2_17_18.model.Alimento;

public class MainActivity extends AppCompatActivity {

    private static final int CONSULTAR_ALIMENTO = 1;
    private EditText etNombreMAIN;
    private EditText etTipoMAIN;
    private EditText etOrigenMAIN;
    private EditText etNutrientesMAIN;
    private EditText etFuncionMAIN;

    private AlimentoDataSource ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombreMAIN = findViewById(R.id.etNombreMAIN);
        etTipoMAIN = findViewById(R.id.etTipoMAIN);
        etOrigenMAIN = findViewById(R.id.etOrigenMAIN);
        etNutrientesMAIN = findViewById(R.id.etNutrientesMAIN);
        etFuncionMAIN = findViewById(R.id.etFuncionMAIN);

        ads = new AlimentoDataSource(this);
    }

    public void c_btnRegistrarMAIN(View v){
        String nombre = etNombreMAIN.getText().toString();
        String tipo = etTipoMAIN.getText().toString();
        String origen = etOrigenMAIN.getText().toString();
        String nutrientes = etNutrientesMAIN.getText().toString();
        String funcion = etFuncionMAIN.getText().toString();

        if(nombre.trim().isEmpty() || tipo.trim().isEmpty() || origen.trim().isEmpty() || nutrientes.trim().isEmpty() || funcion.trim().isEmpty()){
            Toast.makeText(this,"Todos los campos son obligatorios para almacenar",Toast.LENGTH_LONG).show();
        }
        else {
            Alimento alimento = new Alimento(nombre,tipo,origen,nutrientes,funcion);

            long result = ads.registrarAlimento(alimento);
            if(result != -1){
                Toast.makeText(this,"El alimento ha sido registrado",Toast.LENGTH_LONG).show();
                limpiarInserccion();
            }
            else{
                Toast.makeText(this,"Error al regirstrar el alimento",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void c_btnLimpiarMAIN(View v){
        limpiarInserccion();
    }

    public void c_btnConsultarMAIN(View v){
        String nombre = etNombreMAIN.getText().toString();
        if(nombre.trim().isEmpty()){
            Toast.makeText(this,"La consultar se busca por el nombre del alimento",Toast.LENGTH_LONG).show();
        }
        else{
            Intent i = new Intent(this,DatosActivity.class);
            i.putExtra("NOMBRE_ALIMENTO",nombre);
            startActivityForResult(i,CONSULTAR_ALIMENTO);
        }
    }

    private void limpiarInserccion() {
        etNombreMAIN.setText("");
        etTipoMAIN.setText("");
        etOrigenMAIN.setText("");
        etNutrientesMAIN.setText("");
        etFuncionMAIN.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONSULTAR_ALIMENTO){
            if(resultCode == RESULT_OK){
                limpiarInserccion();
            }
        }
    }
}
