package br.com.mark.transacaoapi.infra.util;


import br.com.mark.transacaoapi.core.Statistics;
import br.com.mark.transacaoapi.usecases.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationsForBeans {




    @Bean
    public Transaction transaction (){
        return new Transaction();
    }

    @Bean
    public Statistics estatistica (){
        return new Statistics();
    }





}
