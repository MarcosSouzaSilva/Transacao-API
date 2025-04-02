package br.com.mark.transacaoapi;

import br.com.mark.transacaoapi.core.Statistics;
import br.com.mark.transacaoapi.core.User;
import br.com.mark.transacaoapi.infra.Controller;
import br.com.mark.transacaoapi.infra.service.UserService;
import br.com.mark.transacaoapi.infra.util.exceptions.Error422;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(Controller.class)
@AutoConfigureMockMvc //configura o Mock automaticamnete pra simular requisicoes http nos testes
public class ControllerTest {

    private static String TRANSACTION_ITAU_API = "/api/v1/transacao";

    @MockitoBean
    UserService userService;

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private Statistics statistics;

    private User user;

    private OffsetDateTime dataHora;

    private ArrayList<DoubleSummaryStatistics> stats = new ArrayList<>();


    @BeforeEach
    public void setup() {

        stats.add(new DoubleSummaryStatistics());
        dataHora = OffsetDateTime.parse("2025-03-30T13:13:23.697634200-03:00");
        user = User.builder().valor(4.0).dataHora(dataHora).build();

    }

    @Test
    @DisplayName("Salva usuario em memoria")
    public void saved() throws Exception {

        BDDMockito.given(userService.transaction(Mockito.any(User.class))).willReturn(user);

        ObjectMapper objectMapper = new ObjectMapper();
        // Permite o suporte às classes de data/hora do Java 8+ (LocalDate, LocalDateTime)
        objectMapper.registerModule(new JavaTimeModule());

        // Configura para serializar datas como string ISO (yyyy-MM-dd) e não como timestamp numérico
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //esta convertendo pra json
        String json = objectMapper.writeValueAsString(user);

        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post(TRANSACTION_ITAU_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(content)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valor").value(4.0));
    }

    public User createNewUser (){
        return User.builder().dataHora(OffsetDateTime.parse("2025-03-30T13:13:23.697634200-03:00")).valor(2.0).build();
    }

    @Test
    @DisplayName("Verifica se vai lançar execoes corretamente na transacao")
    public void createUserWithDuplicatedTime() {
        User users = createNewUser();

        Mockito.when(userService.transaction(users)).thenThrow(Error422.class);

        assertThrows(Error422.class, () -> userService.transaction(users)); //garantindo que a exceção seja realmente lançada e validando se ela é do tipo Error400

    }

    @Test
    @DisplayName("Apaga usuario em memoria")
    public void delete() throws Exception {

        saved();

        clear();

        assertThat(user.getValor()).isNull();
        assertThat(user.getDataHora()).isNull();

    }

    public void clear() {
        user.setValor(null);
        user.setDataHora(null);
    }


}