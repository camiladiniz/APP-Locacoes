package br.com.locacoes.fpprojetos.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.locacoes.fpprojetos.R;
import br.com.locacoes.fpprojetos.adapter.LocacaoAdapterRecycle;
import br.com.locacoes.fpprojetos.models.Locacao;

/**
 * Created by adminLocal on 27/04/2018.
 */

public class LocacaoHolder extends RecyclerView.ViewHolder{

    private final LocacaoAdapterRecycle adapter;
    private Long locacaoId;
    private TextView campoNome;
    private TextView dataInicio;
    private TextView dataDevolucao;
    private TextView origem;
    private TextView destino;

    public LocacaoHolder(final View itemView, LocacaoAdapterRecycle adapter) {
        super(itemView);
        this.adapter = adapter;
        campoNome = itemView.findViewById(R.id.nome);
        dataInicio = itemView.findViewById(R.id.dataInicio);
        dataDevolucao = itemView.findViewById(R.id.dataDevolucao);
        origem =itemView.findViewById(R.id.origem);
        destino = itemView.findViewById(R.id.destino);
    }

    public void preencher(Locacao locacao){
        locacaoId = locacao.getId();
        campoNome.setText(locacao.getNome());
        dataInicio.setText(locacao.getDataInicio());
        dataDevolucao.setText(locacao.getDataDevolucao());
        origem.setText(locacao.getOrigem());
        destino.setText(locacao.getDestino());
    }
}
