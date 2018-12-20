package br.com.locacoes.fpprojetos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.locacoes.fpprojetos.models.Locacao;
import br.com.locacoes.fpprojetos.models.Veiculo;

public class LocacaoDAO extends SQLiteOpenHelper {

    public LocacaoDAO(Context context) {
        super(context, "FPProjetos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Locacao(id INTEGER PRIMARY KEY, nome TEXT, origem TEXT, destino TEXT, qntPassageiros INTEGER, dataInicio TEXT, dataDevolucao TEXT, motorista TEXT, idVeiculo INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Locacao";
        db.execSQL(sql);
    }

    private ContentValues getDados(Locacao locacao, Long veiculoId){
        ContentValues dados = new ContentValues();

        dados.put("nome", locacao.getNome());
        dados.put("origem", locacao.getOrigem());
        dados.put("destino", locacao.getDestino());
        dados.put("qntPassageiros", locacao.getQntPassageiros());
        dados.put("dataInicio", locacao.getDataInicio());
        dados.put("dataDevolucao", locacao.getDataDevolucao());
        dados.put("motorista", locacao.getMotorista());
        dados.put("idVeiculo", veiculoId);

        return dados;
    }

    public void inserir(Locacao locacao, Long veiculoId){
        SQLiteDatabase liteDb = getWritableDatabase();
        ContentValues dados = getDados(locacao, veiculoId);

        liteDb.insert("Locacao",null, dados);
    }

    public List<Locacao> buscarLocacao(){
        String sql = "SELECT * FROM Locacao";

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(sql, null);
        List<Locacao> locacoes = new ArrayList<Locacao>();
        while (c.moveToNext()){
            Locacao locacao = new Locacao();

            locacao.setId(c.getLong(c.getColumnIndex("id")));
            locacao.setOrigem(c.getString(c.getColumnIndex("origem")));
            locacao.setDestino(c.getString(c.getColumnIndex("destino")));
            locacao.setDataInicio(c.getString(c.getColumnIndex("inicio")));
            locacao.setQntPassageiros(c.getInt(c.getColumnIndex("qntPassageiros")));
            locacao.setDataInicio(c.getString(c.getColumnIndex("dataInicio")));
            locacao.setDataDevolucao(c.getString(c.getColumnIndex("dataDevolucao")));
            locacao.setDataDevolucao(c.getString(c.getColumnIndex("motorista")));
            //BUSCAR ID VEICULO

            locacoes.add(locacao);
        }
            c.close();
            return locacoes;
    }

    public Locacao localizar(Long locacaoId){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Locacao WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(locacaoId)});
        c.moveToFirst();

        Locacao locacaoRetornada = new Locacao();
        locacaoRetornada.setId(c.getLong(c.getColumnIndex("id")));
        locacaoRetornada.setOrigem(c.getString(c.getColumnIndex("origem")));
        locacaoRetornada.setDestino(c.getString(c.getColumnIndex("destino")));
        locacaoRetornada.setDataInicio(c.getString(c.getColumnIndex("inicio")));
        locacaoRetornada.setQntPassageiros(c.getInt(c.getColumnIndex("qntPassageiros")));
        locacaoRetornada.setDataInicio(c.getString(c.getColumnIndex("dataInicio")));
        locacaoRetornada.setDataDevolucao(c.getString(c.getColumnIndex("dataDevolucao")));
        locacaoRetornada.setDataDevolucao(c.getString(c.getColumnIndex("motorista")));

        db.close();

        return  locacaoRetornada;
    }

}
