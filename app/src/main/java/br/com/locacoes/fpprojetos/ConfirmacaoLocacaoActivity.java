package br.com.locacoes.fpprojetos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmacaoLocacaoActivity extends AppCompatActivity {

    private TextView status, valorLocacao, valorMotorista, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao_locacao);

        status = findViewById(R.id.status);
        valorLocacao = findViewById(R.id.valorLocacao);
        valorMotorista = findViewById(R.id.valorMotorista);
        total = findViewById(R.id.total);

        Bundle extras = getIntent().getExtras();
        String diferenca = (extras != null)? extras.getString("diferenca"): null;
        Double resultado = (extras != null)? extras.getDouble("resultado") : null;

        status.setText("Locação realizada com sucesso!");
        valorLocacao.setText("Valor total da locação: " +diferenca);


        //valor mototrista
        //valor total




    }
}
