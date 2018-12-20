package br.com.locacoes.fpprojetos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.locacoes.fpprojetos.R;
import br.com.locacoes.fpprojetos.holder.VeiculosHolder;
import br.com.locacoes.fpprojetos.models.Veiculo;

public class VeiculoAdapterRecycle extends RecyclerView.Adapter{

    private final Context context;
    private final List<Veiculo> veiculos;

    public VeiculoAdapterRecycle(Context context, List<Veiculo> veiculos) {
        this.context = context;
        this.veiculos = veiculos;
    }

    /**
     * Captura e insere na lista
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista, parent, false);
        VeiculosHolder holder = new VeiculosHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VeiculosHolder nossoHolder = (VeiculosHolder) holder;
        Veiculo veiculo = veiculos.get(position);
        nossoHolder.preencher(veiculo);
    }

    @Override
    public int getItemCount() {
        return veiculos.size();
    }
}
