package br.com.mark.transacaoapi.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    private Long count;

    private double sum;

    private double avg;

    private double min;

    private double max;


}