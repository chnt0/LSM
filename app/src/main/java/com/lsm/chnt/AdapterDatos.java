package com.lsm.chnt;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos>{
    List<String> listDatos;
    List<String> listvideos;

    public AdapterDatos(List<String> listDatos,List<String> listvideos) {
        this.listDatos = listDatos;
        this.listvideos=listvideos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i) {
        viewHolderDatos.asignarDatos(listDatos.get(i),listvideos.get(i));

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
        String video;
        public ViewHolderDatos(@NonNull final View itemView) {
            super(itemView);
            dato=itemView.findViewById(R.id.idDato);
            dato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(itemView.getContext(),dato.getText(),Toast.LENGTH_LONG).show();
                    Intent homeIntent= new Intent(itemView.getContext(),videoActivity.class);
                    homeIntent.putExtra("VIDEO_ID", video);
                    itemView.getContext().startActivity(homeIntent);
                }
            });
        }

        public void asignarDatos(String s,String v) {
            dato.setText(s);
            video=v;
        }
    }
}
