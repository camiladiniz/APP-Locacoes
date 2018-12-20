package br.com.locacoes.fpprojetos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.locacoes.fpprojetos.adapter.VeiculoAdapterRecycle;
import br.com.locacoes.fpprojetos.dao.VeiculoDAO;
import br.com.locacoes.fpprojetos.models.Veiculo;

public class MainActivity extends AppCompatActivity {

    private Button btnAddVeiculo;
    RecyclerView recycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddVeiculo = findViewById(R.id.btnAdicionarVeiculo);
        recycleList = findViewById(R.id.recycleList);
        carregarListaRecycle();


        btnAddVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroVeiculoActivity.class);
                startActivity(intent);
            }
        });



    }

    private void carregarListaRecycle(){
        VeiculoDAO dao = new VeiculoDAO(this);
        List<Veiculo> veiculos = dao.buscaVeiculo();
        VeiculoAdapterRecycle adapter = new VeiculoAdapterRecycle(this, veiculos);
        recycleList.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycleList.setLayoutManager(layoutManager);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        carregarListaRecycle();
    }
}
