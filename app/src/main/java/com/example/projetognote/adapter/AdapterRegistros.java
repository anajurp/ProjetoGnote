package com.example.projetognote.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetognote.R;
import com.example.projetognote.model.Registro;

import java.util.List;

public class AdapterRegistros extends RecyclerView.Adapter<AdapterRegistros.MyViewHolder> {

    private List<Registro> listaRegistros;
    private OnRegistroListener registroListener;

    private Registro registro;

    public AdapterRegistros(List<Registro> listaRegistros, OnRegistroListener onRegistroListener) {
        this.listaRegistros = listaRegistros;
        this.registroListener = onRegistroListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista, parent, false);
        return new MyViewHolder(itemLista, registroListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Registro registro = listaRegistros.get(position);
//        holder.hora.setText(registro.getHoraRegistro());
        holder.glicose.setText(registro.getRegistroGlicose());
        holder.insulinaFixa.setText((int) registro.getInsulinaFixa());
        holder.insulinaCorrecao.setText((int) registro.getInsulinaCorrecao());


    }

    // quantidade de itens que vai retornar do ViewHolder
    @Override
    public int getItemCount() {
        return this.listaRegistros.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView etiqueta;
        private TextView data, hora, glicose, insulinaFixa, insulinaCorrecao;
        OnRegistroListener onRegistroListener;

        public MyViewHolder(@NonNull View itemView, OnRegistroListener onRegistroListener) {
            super(itemView);

            data = itemView.findViewById(R.id.tv_data);
            hora = itemView.findViewById(R.id.tv_hora);
            glicose = itemView.findViewById(R.id.tv_glicose);
            insulinaFixa = itemView.findViewById(R.id.tv_insulina_refeicao);
            insulinaCorrecao = itemView.findViewById(R.id.tv_insulina_correcao);
            etiqueta = itemView.findViewById(R.id.iv_etiqueta);

            this.onRegistroListener = onRegistroListener;
            itemView.setOnClickListener(this);

            // 1 hipo; 2 bom; 3 hiper
            if(registro.getEtiqueta() == String.valueOf(2)){
                // bom
                etiqueta.setImageResource(R.drawable.muito_boa);
            }else if(registro.getEtiqueta() == String.valueOf(1)){
                // hipo
                etiqueta.setImageResource(R.drawable.hipo);
            }else if(registro.getEtiqueta() == String.valueOf(3)){
                // hiper
                etiqueta.setImageResource(R.drawable.hiper);
            }else{
                // normal - imagem padrão
                etiqueta.setImageResource(R.drawable.boa_normal);
            }
        }

        @Override
        public void onClick(View v) {
            onRegistroListener.onRegistroClick(getAdapterPosition());

        }
    }

    public interface OnRegistroListener{
        void onRegistroClick(int position);
    }

}
