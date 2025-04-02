package br.com.mark.transacaoapi.infra;

import br.com.mark.transacaoapi.core.Statistics;
import br.com.mark.transacaoapi.core.User;
import br.com.mark.transacaoapi.infra.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("api/v1")
public class Controller {

    private final UserService service;

    private Instant tempoInicial = Instant.now();

    private ArrayList<DoubleSummaryStatistics> stats = new ArrayList<>();

    private Statistics statistics;

    public Controller(UserService service, Statistics statistics) {
        this.service = service;
        this.statistics = statistics;
        stats.add(new DoubleSummaryStatistics());
    }

    @PostMapping("/transacao")
    public ResponseEntity<?> transaction(@RequestBody User user) {

        var newUser = service.transaction(user);

        stats.forEach(s -> {
            s.accept(newUser.getValor());
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete() {
        return service.delete();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<?> statistics() {
        Instant agora = Instant.now();

        Long segundosDecorridos = Duration.between(tempoInicial, agora).getSeconds();

        System.out.println(segundosDecorridos);

        if (segundosDecorridos >= 60) {

            statistics.setAvg(0);
            statistics.setSum(0);
            statistics.setMin(0);
            statistics.setMax(0);
            statistics.setCount(0L);

            stats.clear();

            stats.add(new DoubleSummaryStatistics());

            tempoInicial = Instant.now();

        } else {

            stats.forEach(s -> {
                statistics.setAvg(s.getAverage());
                statistics.setSum(s.getSum());
                statistics.setMin(s.getMin());
                statistics.setMax(s.getMax());
                statistics.setCount(s.getCount());
            });

        }

        return ResponseEntity.status(HttpStatus.OK).body(statistics);
    }


}