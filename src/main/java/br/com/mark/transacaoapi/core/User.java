package br.com.mark.transacaoapi.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public class User {

    @JsonProperty("valor")
    private Double valor;

    @JsonProperty("dataHora")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime dataHora;


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public OffsetDateTime  getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime  dataHora) {
        this.dataHora = dataHora;
    }

}