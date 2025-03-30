package br.com.mark.transacaoapi;

import br.com.mark.transacaoapi.core.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class TransactionTest {


    @BeforeEach
    public void setup() {

    }


    @Test
    @DisplayName("Salva usuário em memoria")
    public void verifyExceptions() {

        User user = User.builder().valor(null).dataHora(null).build();



        assertThat(user.getValor()).isNotNull();
        assertThat(user.getDataHora()).isNotNull();

    }


}