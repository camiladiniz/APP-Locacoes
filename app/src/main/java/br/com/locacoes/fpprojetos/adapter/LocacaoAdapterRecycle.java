package br.com.locacoes.fpprojetos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.locacoes.fpprojetos.R;
import br.com.locacoes.fpprojetos.holder.LocacaoHolder;
import br.com.locacoes.fpprojetos.models.Locacao;

/**
 * Created by adminLocal on 27/04/2018.
 */

public class LocacaoAdapterRecycle extends RecyclerView.Adapter{

    private final Context context;
    private final List<Locacao> locacoes;

    public LocacaoAdapterRecycle(Context context, List<Locacao> locacoes) {

        this.context = context;
        this.locacoes = locacoes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.locacoes_lista,parent, false);
        LocacaoHolder holder= new LocacaoHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LocacaoHolder nossoHolder = (LocacaoHolder) holder;
        Locacao locacao = locacoes.get(position);
        nossoHolder.preencher(locacao);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
