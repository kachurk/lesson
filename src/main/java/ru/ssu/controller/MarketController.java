package ru.ssu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ssu.dto.TransactionDto;
import ru.ssu.dto.TransactionStatus;
import ru.ssu.exception.NotEnoughBalanceException;
import ru.ssu.service.MarketEngine;

@RestController
@RequestMapping("/v1/market/")
public class MarketController {


    @Autowired
    private MarketEngine marketEngine;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> transaction(@RequestBody TransactionDto transactionDto) {
        try {
            TransactionDto dto = marketEngine.executeTransaction(transactionDto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (NotEnoughBalanceException e) {
            TransactionDto dto = makeFailedTransaction(transactionDto, e);

            return new ResponseEntity<>(dto, HttpStatus.OK);

        }
    }

    private TransactionDto makeFailedTransaction(@RequestBody TransactionDto transactionDto, NotEnoughBalanceException e) {
        return new TransactionDto(transactionDto.getUserId(),
                        transactionDto.getItemId(),
                        TransactionStatus.FAILED,
                        e.getMessage());
    }

}
