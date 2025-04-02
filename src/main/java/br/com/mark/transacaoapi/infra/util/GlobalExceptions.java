package br.com.mark.transacaoapi.infra.util;

import br.com.mark.transacaoapi.infra.util.exceptions.Error400;
import br.com.mark.transacaoapi.infra.util.exceptions.Error422;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {


    @ExceptionHandler(Error422.class)
    public ResponseEntity<?> handleIdentifierError422Exception() {
        System.out.println("Entrou no erro que eu queria");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @ExceptionHandler(Error400.class)
    public ResponseEntity<?> handleIdentifierBadRequestException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



    }