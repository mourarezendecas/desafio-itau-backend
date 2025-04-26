package desafio_itau.rezendecas.spring_artifact.controller;

import desafio_itau.rezendecas.spring_artifact.dto.StatisticsResponse;
import desafio_itau.rezendecas.spring_artifact.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    private final TransactionService transactionService;

    public StatisticsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<StatisticsResponse> getStatistics() {
        DoubleSummaryStatistics stats = transactionService.getStatistics();

        return ResponseEntity.ok(new StatisticsResponse(stats));
    }
}
