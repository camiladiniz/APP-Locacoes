package br.com.locacoes.fpprojetos;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import br.com.locacoes.fpprojetos.adapter.VeiculoAdapterRecycle;
import br.com.locacoes.fpprojetos.dao.LocacaoDAO;
import br.com.locacoes.fpprojetos.dao.VeiculoDAO;
import br.com.locacoes.fpprojetos.helper.CadastroVeiculoHelper;
import br.com.locacoes.fpprojetos.helper.LocacaoVeiculoHelper;
import br.com.locacoes.fpprojetos.holder.VeiculosHolder;
import br.com.locacoes.fpprojetos.models.Locacao;
import br.com.locacoes.fpprojetos.models.Veiculo;

public class LocacaoVeiculoActivity extends AppCompatActivity {

    private VeiculoDAO dao;
    private TextView marca;
    private TextView modelo;
    private TextView placa;
    private TextView qnt;
    private TextView valorDiaria;

    private DatePickerDialog.OnDateSetListener escutadorDataInicio;
    private DatePickerDialog.OnDateSetListener escutadorDataDevolucao;
    private String dateInicio, dateDevolucao;
    private LocalDate dataInicio, dataDevolucao;
    private EditText campoDataInicio, campoDataDevolucao, diariaValue;
    private Button alugar;
    private double diaria;
    Long veiculoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locacao_veiculo);

        dao = new VeiculoDAO(this);
        marca = findViewById(R.id.marca);
        modelo = findViewById(R.id.modelo);
        placa = findViewById(R.id.placa);
        qnt = findViewById(R.id.qntMaximaPassageiros);

        campoDataInicio = findViewById(R.id.dataInicio);
        campoDataDevolucao = findViewById(R.id.dataDevolucao);
        alugar = findViewById(R.id.btnAlugar);
        diariaValue = findViewById(R.id.diariaValue);


        Bundle extras = getIntent().getExtras();

        if(extras != null){
            veiculoId = extras.getLong("veiculoId");
            String marcaV = extras.getString("marca");
            String modeloV = extras.getString("modelo");
            String placaV = extras.getString("placa");
            String qntV = extras.getString("qntPessoas");
            String valor = extras.getString("valorDiaria");

            modelo.setText(String.valueOf("Modelo: " +modeloV));
            marca.setText("Marca: " +marcaV);
            placa.setText("Placa: " +placaV);
            qnt.setText("Quaantidade máxima de pessoas suportada: " +String.valueOf(qntV));
            diariaValue.setText(valor);
        }

        valorDiaria = findViewById(R.id.valorDiaria);
        diaria = Double.parseDouble(diariaValue.getText().toString());

        campoDataInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(LocacaoVeiculoActivity.this, escutadorDataInicio, year, month, day);
                dialog.show();

            }
        });

        campoDataDevolucao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(LocacaoVeiculoActivity.this, escutadorDataDevolucao, year, month, day);
                dialog.show();

            }
        });

        escutadorDataInicio = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateInicio = dayOfMonth+"/"+month+"/"+year;
                campoDataInicio.setText(dateInicio);
                dataInicio = LocalDate.of(year, month, dayOfMonth);
            }
        };

        escutadorDataDevolucao = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateDevolucao = dayOfMonth+"/"+month+"/"+year;
                campoDataDevolucao.setText(dateDevolucao);
                dataDevolucao = LocalDate.of(year, month, dayOfMonth);
            }
        };

        final LocacaoVeiculoHelper helper = new LocacaoVeiculoHelper(this);

        alugar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Long dif = dataInicio.until(dataDevolucao, ChronoUnit.DAYS);
                Intent intent = new Intent(LocacaoVeiculoActivity.this, ConfirmacaoLocacaoActivity.class);
                String diferenca = String.valueOf(dif);

                double resultado = Integer.parseInt(diferenca) * diaria;
                intent.putExtra("diferenca", diferenca);
                intent.putExtra("valorDiferenca", resultado);


                Locacao locacao = helper.pegarLocacao();
                LocacaoDAO dao = new LocacaoDAO(LocacaoVeiculoActivity.this);
                dao.inserir(locacao, veiculoId);
                Toast.makeText(getApplicationContext(), "Locação concluida !", Toast.LENGTH_LONG).show();
                dao.close();
                finish();
                startActivityForResult(intent, 1);
            }
        });



    }
}
