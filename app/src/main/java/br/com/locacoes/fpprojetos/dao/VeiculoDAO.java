package br.com.locacoes.fpprojetos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.locacoes.fpprojetos.models.Veiculo;

public class VeiculoDAO extends SQLiteOpenHelper{


    public VeiculoDAO(Context context) {
        super(context, "FPProjetos", null, 1);
    }

    /**
     * Criando a tabela
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Veiculo(id INTEGER PRIMARY KEY, caminhoFoto TEXT, onibusOuVan TEXT, modelo TEXT, marca TEXT, placa TEXT, qntMaximaPassageiros INTEGER, valorDiaria REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Veiculo";
        db.execSQL(sql);
    }

    private ContentValues getDados(Veiculo veiculo){
        ContentValues dados = new ContentValues();

        dados.put("caminhoFoto", veiculo.getCaminhoFoto());
        dados.put("onibusOuVan", String.valueOf(veiculo.getOnibusOuVan()));
        dados.put("modelo", veiculo.getModelo());
        dados.put("marca", veiculo.getMarca());
        dados.put("placa", veiculo.getPlaca());
        dados.put("qntMaximaPassageiros", veiculo.getQntMaximaPassageiros());
        dados.put("valorDiaria", veiculo.getValorDiaria());

        return dados;
    }

    public void inserir (Veiculo veiculo){

        //Capturando e tratando os dados
        SQLiteDatabase liteDb = getWritableDatabase();
        ContentValues dados = getDados(veiculo);

        liteDb.insert("Veiculo", null, dados);
    }

    public List<Veiculo> buscaVeiculo(){

        String sql = "SELECT * FROM Veiculo";

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(sql, null);
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        while (c.moveToNext()){
            Veiculo veiculo = new Veiculo();
            veiculo.setId(c.getLong(c.getColumnIndex("id")));
            veiculo.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            veiculo.setOnibusOuVan(c.getString(c.getColumnIndex("onibusOuVan")));
            veiculo.setModelo(c.getString(c.getColumnIndex("modelo")));
            veiculo.setMarca(c.getString(c.getColumnIndex("marca")));
            veiculo.setPlaca(c.getString(c.getColumnIndex("placa")));
            veiculo.setQntMaximaPassageiros(c.getInt(c.getColumnIndex("qntMaximaPassageiros")));
            veiculo.setValorDiaria(c.getDouble(c.getColumnIndex("valorDiaria")));

            veiculos.add(veiculo);
        }
        c.close();
        return veiculos;
    }

    public Veiculo localizar (Long veiculoId){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Veiculo WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(veiculoId)});
        c.moveToFirst();
        Veiculo veiculoRetornado = new Veiculo();

        veiculoRetornado.setId(c.getLong(c.getColumnIndex("id")));
        veiculoRetornado.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
        veiculoRetornado.setModelo(c.getString(c.getColumnIndex("modelo")));
        veiculoRetornado.setMarca(c.getString(c.getColumnIndex("marca")));
        veiculoRetornado.setPlaca(c.getString(c.getColumnIndex("placa")));
        veiculoRetornado.setOnibusOuVan(c.getString(c.getColumnIndex("onibusOuVan")));
        veiculoRetornado.setQntMaximaPassageiros(c.getInt(c.getColumnIndex("qntMaximaPassageiros")));
        veiculoRetornado.setValorDiaria(c.getDouble(c.getColumnIndex("valorDiaria")));

        db.close();

        return veiculoRetornado;
    }
}
