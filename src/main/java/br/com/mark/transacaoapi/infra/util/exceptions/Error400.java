package br.com.mark.transacaoapi.infra.util.exceptions;

public class Error400 extends RuntimeException {

    public Error400(String message) {
        super(message);
    }

    public Error400() {
    }
}
