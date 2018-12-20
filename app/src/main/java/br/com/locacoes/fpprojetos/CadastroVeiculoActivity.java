package br.com.locacoes.fpprojetos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.locacoes.fpprojetos.dao.VeiculoDAO;
import br.com.locacoes.fpprojetos.helper.CadastroVeiculoHelper;
import br.com.locacoes.fpprojetos.models.Veiculo;

public class CadastroVeiculoActivity extends AppCompatActivity {

    public static final int GALERIA_CODE = 1;
    public static final int PERMISSAO_REQUEST = 1;
    private Button btnCadastrar;
    private VeiculoDAO dao;
    private ImageView fotoVeiculo;
    private Button btnVerLocacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        dao = new VeiculoDAO(this);
        fotoVeiculo = findViewById(R.id.imgVeiculo);
        btnVerLocacoes = findViewById(R.id.listaLocacoes);

        btnVerLocacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroVeiculoActivity.this, ListaLocacoesActivity.class);
                startActivity(intent);
            }
        });

        fotoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_CODE);
            }
        });

        final CadastroVeiculoHelper helper = new CadastroVeiculoHelper(this);

        Veiculo veiculo = new Veiculo();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veiculo veiculo = helper.pegaVeiculo(CadastroVeiculoActivity.this);
                VeiculoDAO dao = new VeiculoDAO(CadastroVeiculoActivity.this);

                dao.inserir(veiculo);

                dao.close();
                finish();
            }
        });

        //Salvando a img carregada
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){

            }else{
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // / A permissão foi concedida. Pode continuar
        } else{
            // A permissão foi negada. Precisa ver o que deve ser desabilitado

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == GALERIA_CODE){
            Uri imgSelecionada = data.getData();

            String[] diretorio = {MediaStore.Images.Media.DATA};

            Cursor c = getContentResolver().query(imgSelecionada, diretorio, null, null, null);

            c.moveToFirst();

            int columnIndex = c.getColumnIndex(diretorio[0]);

            String caminhoImagem = c.getString(columnIndex);
            c.close();

            Bitmap imgRetornada = (BitmapFactory.decodeFile(caminhoImagem));
            fotoVeiculo.setImageBitmap(imgRetornada);
            fotoVeiculo.setTag(caminhoImagem);

        }


    }
}
