package br.com.mark.transacaoapi;

import br.com.mark.transacaoapi.core.Statistics;
import br.com.mark.transacaoapi.core.User;
import br.com.mark.transacaoapi.infra.Controller;
import br.com.mark.transacaoapi.infra.service.UserService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(Controller.class)
@AutoConfigureMockMvc //configura o Mock automaticamnete pra simular requisicoes http nos testes
public class ControllerTest {

    private static String BOOK_API = "/api/v1/transacao";

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
    public void setup () {
        /*saved();
        delete();*/
        stats.add(new DoubleSummaryStatistics());
        dataHora = OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00");
        user = User.builder().valor(4.0).dataHora(dataHora).build();

        stats.forEach(p -> {
            p.accept(user.getValor());
        });

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

        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(content)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valor").value(4.0));
    }

    public ResponseEntity<Object> deletes() {

        stats.clear();

        stats.add(new DoubleSummaryStatistics());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Test
    @DisplayName("Apaga usuario em memoria")
    public void delete () throws Exception {

        saved();

        System.err.println(user.getValor());

        stats.clear();

        stats.add(new DoubleSummaryStatistics());

        System.err.println(user.getValor());

        assertThat(user.getValor()).isNull();
        assertThat(user.getDataHora()).isNull();



    }




    /*@Test
    @DisplayName("Salva usuario")*/
    public void saveUser() {

        User user = User.builder().valor(4.0).dataHora(OffsetDateTime.now()).build();

        Mockito.when(userService.transaction(user)).thenReturn(user);

        assertThat(user.getValor()).isEqualTo(4.0);

    }


}