package com.arquitecturaviva.actrices;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputEditText img = findViewById(R.id.etImagenUrl);
        TextInputEditText nombre = findViewById(R.id.etNombre);
        TextInputEditText descripcion = findViewById(R.id.etDescripcion);
        TextInputEditText edad = findViewById(R.id.etEdad);
        TextInputEditText genero = findViewById(R.id.etGenero);
        TextInputEditText nacionalidad = findViewById(R.id.etNacionalidad);
        TextInputEditText estatura = findViewById(R.id.etEstatura);
        SwitchMaterial premio = findViewById(R.id.swPremio);
        Button cancelar = findViewById(R.id.btnCancelar);
        Button guardar = findViewById(R.id.btnGuardar);

        Retrofit retro = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080").addConverterFactory(GsonConverterFactory.create()).build();
        ActrizConn conn = retro.create(ActrizConn.class);

        int id = getIntent().getIntExtra("id", -1);

        if (id != -1) {
            conn.getActri(id).enqueue(new Callback<Actriz>() {
                @Override
                public void onResponse(Call<Actriz> call, Response<Actriz> response) {
                    if (response.isSuccessful()) {
                        Actriz act = response.body();
                        img.setText(act.getImagen());
                        nombre.setText(act.getName());
                        descripcion.setText(act.getDescription());
                        edad.setText(act.getAge() + "");
                        genero.setText(act.getGender());
                        nacionalidad.setText(act.getNationality());
                        estatura.setText(act.getHeight() + "");
                        premio.setChecked(act.getPrize());
                    }
                }

                @Override
                public void onFailure(Call<Actriz> call, Throwable t) {
                }
            });

            guardar.setOnClickListener(view -> {
                String imagenAct = img.getText().toString();
                String nombreAct = nombre.getText().toString();
                String descripcionAct = descripcion.getText().toString();
                int edadAct = Integer.parseInt(edad.getText().toString());
                String generoAct = genero.getText().toString();
                String nacionalidadAct = nacionalidad.getText().toString();
                double estaturaAct = Double.parseDouble(estatura.getText().toString());
                boolean premioAct = premio.isChecked();

                Actriz act = new Actriz(null, nombreAct, descripcionAct, edadAct, generoAct, nacionalidadAct, estaturaAct, premioAct, imagenAct);

                conn.updateActriz(act).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Actrices.getListaActrices().replaceAll(a -> a.getId() == id ? act : a);
                        Toast.makeText(EditarActivity.this, "Actriz actualizada correctamente", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            });
        } else {
            guardar.setOnClickListener(view -> {
                String imagenAct = img.getText().toString();
                String nombreAct = nombre.getText().toString();
                String descripcionAct = descripcion.getText().toString();
                int edadAct = Integer.parseInt(edad.getText().toString());
                String generoAct = genero.getText().toString();
                String nacionalidadAct = nacionalidad.getText().toString();
                double estaturaAct = Double.parseDouble(estatura.getText().toString());
                boolean premioAct = premio.isChecked();

                Actriz act = new Actriz(null, nombreAct, descripcionAct, edadAct, generoAct, nacionalidadAct, estaturaAct, premioAct, imagenAct);

                conn.createActriz(act).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Actrices.getListaActrices().add(act);
                        Toast.makeText(EditarActivity.this, "Actriz agregada correctamente", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            });
        }

    }
}