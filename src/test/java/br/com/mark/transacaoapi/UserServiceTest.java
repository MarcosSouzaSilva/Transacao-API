package br.com.mark.transacaoapi;

import br.com.mark.transacaoapi.core.User;
import br.com.mark.transacaoapi.infra.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.OffsetDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {

    UserService userService;



    @Test
    @DisplayName("Salva usuário em memoria")
    public void savedUserTest (){
        //cenario

        User user = new User();

        user.setValor(2.0);
        user.setDataHora(OffsetDateTime.now());

        //execucao
        var executa = userService.transaction(user);

        //verificacao
        assertThat(executa.getValor()).isNotNull();
        assertThat(executa.getDataHora()).isNotNull();
        assertThat(executa.getValor()).isGreaterThan(0.0);

    }

}
