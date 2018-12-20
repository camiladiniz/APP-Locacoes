package br.com.locacoes.fpprojetos.helper;

import android.app.DatePickerDialog;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;

import br.com.locacoes.fpprojetos.LocacaoVeiculoActivity;
import br.com.locacoes.fpprojetos.R;
import br.com.locacoes.fpprojetos.models.Locacao;
import br.com.locacoes.fpprojetos.models.Veiculo;

public class LocacaoVeiculoHelper {

    public Locacao locacao;
    public EditText qntPassageiros;
    public CheckBox motorista;
    public EditText nome, origem,destino, dataInicio,dataDevolucao;

    public LocacaoVeiculoHelper(LocacaoVeiculoActivity form) {
        nome = form.findViewById(R.id.idNome);
        motorista = form.findViewById(R.id.contratarMotorista);
        origem = form.findViewById(R.id.origem);
        destino = form.findViewById(R.id.destino);
        dataInicio =form.findViewById(R.id.dataInicio);
        dataDevolucao = form.findViewById(R.id.dataDevolucao);
        qntPassageiros = form.findViewById(R.id.qntPassageirosNecessaria);
        locacao = new Locacao();
    }

    public Locacao pegarLocacao(){
        locacao.setNome(nome.getText().toString());
        if (motorista.isChecked()){
            locacao.setMotorista(true);
        }else{
            locacao.setMotorista(false);
        }
        locacao.setOrigem(origem.getText().toString());
        locacao.setDestino(destino.getText().toString());
        locacao.setDataInicio(dataInicio.getText().toString());
        locacao.setDataDevolucao(dataDevolucao.getText().toString());
        locacao.setQntPassageiros(Integer.parseInt(qntPassageiros.getText().toString()));
        return locacao;
    }


}
