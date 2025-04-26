package desafio_itau.rezendecas.spring_artifact.controller;

import desafio_itau.rezendecas.spring_artifact.Model.Transaction;
import desafio_itau.rezendecas.spring_artifact.dto.TransactionRequest;
import desafio_itau.rezendecas.spring_artifact.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequest request){
        if (request.getDataHora().isAfter(OffsetDateTime.now()) || request.getValor() <= 0){
            return ResponseEntity.unprocessableEntity().build();
        }

        transactionService.addTransaction(
                new Transaction(request.getValor(), request.getDataHora())
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTranscations(){
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }

}
