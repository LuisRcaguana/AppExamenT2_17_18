package com.example.a21746033.appexament2_17_18;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21746033.appexament2_17_18.db.AlimentoDataSource;
import com.example.a21746033.appexament2_17_18.model.Alimento;

public class DatosActivity extends AppCompatActivity {

    private TextView tvNombreDATOS;
    private TextView tvTipoDATOS;
    private TextView tvOrigenDATOS;
    private TextView tvNutrientesDATOS;
    private TextView tvFuncionDATOS;

    private String nombre;
    private Alimento alimento;

    private AlimentoDataSource ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        tvNombreDATOS = findViewById(R.id.tvNombreDATOS);
        tvTipoDATOS = findViewById(R.id.tvTipoDATOS);
        tvOrigenDATOS = findViewById(R.id.tvOrigenDATOS);
        tvNutrientesDATOS = findViewById(R.id.tvNutrientesDATOS);
        tvFuncionDATOS = findViewById(R.id.tvFuncionDATOS);

        nombre = getIntent().getStringExtra("NOMBRE_ALIMENTO");

        ads = new AlimentoDataSource(this);

        alimento = ads.consultarAlimento(nombre);
        if(alimento != null){
            tvNombreDATOS.setText(alimento.getNombre());
            tvTipoDATOS.setText(alimento.getTipo());
            tvOrigenDATOS.setText(alimento.getOrigen());
            tvNutrientesDATOS.setText(alimento.getNutrientes());
            tvFuncionDATOS.setText(alimento.getFuncion());
        }
        else{
            Toast.makeText(this,"No se ha encontrado ningún alimento con el nombre de "+nombre,Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void c_btnEliminarDATOS(View v){
        crear_dialogo().show();
    }

    private Dialog crear_dialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DatosActivity.this);
        builder.setCancelable(false);
        builder.setMessage("¿Desea eliminar "+nombre+"?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ads.eliminarAlimento(alimento);
                Toast.makeText(DatosActivity.this,"Alimento eliminado",Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }
}