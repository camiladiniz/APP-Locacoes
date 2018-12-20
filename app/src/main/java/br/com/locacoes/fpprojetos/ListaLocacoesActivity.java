package br.com.locacoes.fpprojetos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.locacoes.fpprojetos.adapter.LocacaoAdapterRecycle;
import br.com.locacoes.fpprojetos.dao.LocacaoDAO;
import br.com.locacoes.fpprojetos.models.Locacao;

public class ListaLocacoesActivity extends AppCompatActivity {

    RecyclerView recycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locacoes);

        recycleList= findViewById(R.id.recycleListLocacoes);
        carregarListaRecycle();

    }

    private void carregarListaRecycle(){
        LocacaoDAO dao = new LocacaoDAO(this);
        List<Locacao> locacoes = dao.buscarLocacao();
        LocacaoAdapterRecycle adapter = new LocacaoAdapterRecycle(this, locacoes);
        recycleList.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycleList.setLayoutManager(layoutManager);
    }
}
