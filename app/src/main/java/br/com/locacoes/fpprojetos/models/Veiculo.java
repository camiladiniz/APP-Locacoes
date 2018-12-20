package br.com.locacoes.fpprojetos.models;

import android.widget.CheckBox;
import android.widget.RadioGroup;

public class Veiculo {

    private Long id;
    private String caminhoFoto;
    private String onibusOuVan;
    private String modelo;
    private String marca;
    private String placa;
    private int qntMaximaPassageiros;
    private Double valorDiaria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getOnibusOuVan() {
        return onibusOuVan;
    }

    public void setOnibusOuVan(String onibusOuVan) {
        this.onibusOuVan = onibusOuVan;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String amrca) {
        this.marca = amrca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getQntMaximaPassageiros() {
        return qntMaximaPassageiros;
    }

    public void setQntMaximaPassageiros(int qntMaximaPassageiros) {
        this.qntMaximaPassageiros = qntMaximaPassageiros;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
