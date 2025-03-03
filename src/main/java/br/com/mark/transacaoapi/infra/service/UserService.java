package br.com.mark.transacaoapi.infra.service;

import br.com.mark.transacaoapi.core.User;
import br.com.mark.transacaoapi.usecases.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final Transaction transaction;

    public UserService(Transaction transaction) {
        this.transaction = transaction;
    }


    public void transaction(User user)  {

        User userSaved = transaction.transaction(user);

        /*System.out.println("Valor : " + userSaved.getValor() );
        System.out.println("Data Hora : " + userSaved.getDataHora());*/

        log.info("Endpoint de transação, deu certo, retornou um usuário");
    }

}