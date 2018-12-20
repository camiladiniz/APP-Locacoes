package br.com.locacoes.fpprojetos.models;

import android.app.DatePickerDialog;
import android.widget.Button;

import java.time.LocalDate;

public class Locacao {

    private Long id;
    private int qntPassageiros;
    private String origem, destino;
    private String dataInicio, dataDevolucao;
    private String nome;
    private Boolean motorista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQntPassageiros() {
        return qntPassageiros;
    }

    public void setQntPassageiros(int qntPassageiros) {
        this.qntPassageiros = qntPassageiros;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getMotorista() {
        return motorista;
    }

    public void setMotorista(Boolean motorista) {
        this.motorista = motorista;
    }
}
