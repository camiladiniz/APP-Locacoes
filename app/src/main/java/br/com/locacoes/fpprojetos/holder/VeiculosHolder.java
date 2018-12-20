package br.com.locacoes.fpprojetos.holder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.locacoes.fpprojetos.LocacaoVeiculoActivity;
import br.com.locacoes.fpprojetos.MainActivity;
import br.com.locacoes.fpprojetos.R;
import br.com.locacoes.fpprojetos.adapter.VeiculoAdapterRecycle;
import br.com.locacoes.fpprojetos.models.Veiculo;

public class VeiculosHolder extends RecyclerView.ViewHolder{

    private final VeiculoAdapterRecycle adapter;
    private Long veiculoId;
    private String marcaCampo;
    private String modeloCampo;
    private String placaCampo;
    private String categoriaCampo;
    private String valorCampo;
    private String campoQnt;

    private ImageView imgCarregada;
    private TextView modelo;
    private TextView marca;
    private TextView qntMaxPassageiros;
    private TextView categoria;
    private TextView valor;


    public VeiculosHolder(final View itemView, VeiculoAdapterRecycle adapter) {
        super(itemView);
        this.adapter = adapter;
        imgCarregada = itemView.findViewById(R.id.fotoVeiculo);
        modelo = itemView.findViewById(R.id.modelo);
        marca = itemView.findViewById(R.id.marca);
        qntMaxPassageiros = itemView.findViewById(R.id.qntMaxPassageiros);
        categoria = itemView.findViewById(R.id.categoria);
        valor = itemView.findViewById(R.id.valorDiaria);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Activity context = (Activity) v.getContext();
                final Intent intent = new Intent(context, LocacaoVeiculoActivity.class);
                intent.putExtra("veiculoId", veiculoId);
                intent.putExtra("marca", marcaCampo);
                intent.putExtra("modelo", modeloCampo);
                intent.putExtra("placa", placaCampo);
                intent.putExtra("categoria", categoriaCampo);
                intent.putExtra("qntPessoas", campoQnt);
                intent.putExtra("valorDiaria", valorCampo);
                context.startActivityForResult(intent, 1);
            }
        });



    }

    public void preencher(Veiculo veiculo){
        veiculoId = veiculo.getId();
        marcaCampo = veiculo.getMarca();
        modeloCampo = veiculo.getModelo();
        placaCampo = veiculo.getPlaca();
        categoriaCampo = veiculo.getOnibusOuVan();
        valorCampo = String.valueOf(veiculo.getValorDiaria());
        campoQnt = String.valueOf(veiculo.getQntMaximaPassageiros());
        valorCampo = String.valueOf(veiculo.getValorDiaria());

        Bitmap imgRetornada = (BitmapFactory.decodeFile(veiculo.getCaminhoFoto()));
        imgCarregada.setImageBitmap(imgRetornada);
        modelo.setText(veiculo.getModelo());
        marca.setText(veiculo.getMarca());
        //qntMaxPassageiros.setText(qntMaxPassageiros.getText().toString());
        //qntMaxPassageiros.setText(veiculo.getQntMaximaPassageiros());


        //qntMaxPassageiros.setText(veiculo.getQntMaximaPassageiros());

        categoria.setText(veiculo.getOnibusOuVan());
    }
}
