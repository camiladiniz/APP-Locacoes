package br.com.locacoes.fpprojetos.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import br.com.locacoes.fpprojetos.CadastroVeiculoActivity;
import br.com.locacoes.fpprojetos.R;
import br.com.locacoes.fpprojetos.models.Veiculo;

public class CadastroVeiculoHelper {

    public ImageView fotoVeiculo;
    public RadioGroup onibusOuVan;
    public EditText modelo;
    public EditText marca;
    public EditText placa;
    public EditText qntMaximaPassageiros;
    public EditText valorDiaria;
    public Veiculo veiculo;
    public RadioButton opcaoSelecionada;

    public CadastroVeiculoHelper(CadastroVeiculoActivity form) {
        fotoVeiculo = form.findViewById(R.id.imgVeiculo);
        onibusOuVan = form.findViewById(R.id.categoria);
        modelo = form.findViewById(R.id.modelo);
        marca = form.findViewById(R.id.marca);
        placa = form.findViewById(R.id.placa);
        qntMaximaPassageiros = form.findViewById(R.id.qntMaxPassageiros);
        valorDiaria = form.findViewById(R.id.valorDiaria);


        veiculo = new Veiculo();
    }


    public Veiculo pegaVeiculo(CadastroVeiculoActivity form){

        opcaoSelecionada = form.findViewById(onibusOuVan.getCheckedRadioButtonId());
        veiculo.setCaminhoFoto(fotoVeiculo.getTag().toString());
        veiculo.setOnibusOuVan(opcaoSelecionada.getText().toString());
        veiculo.setModelo(modelo.getText().toString());
        veiculo.setMarca(marca.getText().toString());
        veiculo.setPlaca(placa.getText().toString());
        veiculo.setQntMaximaPassageiros(Integer.parseInt(qntMaximaPassageiros.getText().toString()));
        veiculo.setValorDiaria(Double.valueOf(valorDiaria.getText().toString()));
        return veiculo;
    }

    public void preencherForm(Veiculo veiculo){
        veiculo.setOnibusOuVan(veiculo.getOnibusOuVan());
        veiculo.setValorDiaria(veiculo.getValorDiaria());
        veiculo.setQntMaximaPassageiros(veiculo.getQntMaximaPassageiros());
        veiculo.setPlaca(veiculo.getPlaca());
        veiculo.setMarca(veiculo.getMarca());
        veiculo.setModelo(veiculo.getModelo());

        this.veiculo = veiculo;
    }
}
