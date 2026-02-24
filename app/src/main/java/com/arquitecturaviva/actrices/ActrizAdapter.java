package com.arquitecturaviva.actrices;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ActrizAdapter extends RecyclerView.Adapter<ActrizAdapter.ActrizViewHolder> {

    private List<Actriz> listaActrices;

    // Constructor
    public ActrizAdapter(List<Actriz> listaActrices) {
        this.listaActrices = listaActrices;
    }

    @NonNull
    @Override
    public ActrizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actriz_card, parent, false);
        return new ActrizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActrizViewHolder holder, int position) {
        Actriz actriz = listaActrices.get(position);
        holder.txtNombre.setText(actriz.getName());
        holder.txtEdad.setText(actriz.getAge()+"");

        Glide.with(holder.itemView.getContext())
                .load(actriz.getImagen())
                .into(holder.imgUsuario);

    }

    @Override
    public int getItemCount() {
        return listaActrices.size();
    }

    public static class ActrizViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUsuario;
        TextView txtNombre, txtEdad;

        public ActrizViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUsuario = itemView.findViewById(R.id.imgUsuario);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtEdad = itemView.findViewById(R.id.txtEdad);
        }
    }
}
