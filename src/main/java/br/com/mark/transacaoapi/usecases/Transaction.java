package br.com.mark.transacaoapi.usecases;

import br.com.mark.transacaoapi.core.User;
import br.com.mark.transacaoapi.infra.util.exceptions.Error400;
import br.com.mark.transacaoapi.infra.util.exceptions.Error422;

public class Transaction {


    public User transaction(User user) {

        if (user.getValor() == null) {
            throw new Error400();

        } else if (user.getValor() <= 0.0) {
            throw new Error422();

        } else if (user.getDataHora() == null) {
            throw new Error400();
        }

        return user;
    }

}