package com.arquitecturaviva.actrices;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvActrices;
    ActrizAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Retrofit retro = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080").addConverterFactory(GsonConverterFactory.create()).build();
        ActrizConn conn = retro.create(ActrizConn.class);

        rvActrices = findViewById(R.id.rvActrices);
        rvActrices.setLayoutManager(new GridLayoutManager(this, 2));

        conn.getActrices().enqueue(new Callback<List<Actriz>>() {
            @Override
            public void onResponse(Call<List<Actriz>> call, Response<List<Actriz>> response) {
                if (response.isSuccessful()) {
                    Actrices.getListaActrices().clear();
                    Actrices.getListaActrices().addAll(response.body());

                    adaptador = new ActrizAdapter(Actrices.getListaActrices());
                    rvActrices.setAdapter(adaptador);
                }
            }

            @Override
            public void onFailure(Call<List<Actriz>> call, Throwable t) {
            }
        });

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditarActivity.class);
            startActivity(intent);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adaptador != null) {
            adaptador.notifyDataSetChanged();
        }
    }
}