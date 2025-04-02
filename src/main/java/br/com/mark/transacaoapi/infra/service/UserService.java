package br.com.mark.transacaoapi.infra.service;

import br.com.mark.transacaoapi.core.User;
import br.com.mark.transacaoapi.usecases.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

@Service
public class UserService {

    private ArrayList<DoubleSummaryStatistics> stats = new ArrayList<>();

    public UserService(ArrayList<DoubleSummaryStatistics> stats) {
        stats.add(new DoubleSummaryStatistics());
    }

    public User transaction(User user)  {

        Transaction transaction = new Transaction();

        return transaction.transaction(user);
    }

    public ResponseEntity<?> delete() {

        stats.clear();

        stats.add(new DoubleSummaryStatistics());

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}