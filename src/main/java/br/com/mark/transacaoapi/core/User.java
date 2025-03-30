package br.com.mark.transacaoapi.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty("valor")
    private Double valor;

    @JsonProperty("dataHora")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime dataHora;




}