package com.kata.bankaccount.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kata.bankaccount.dto.MessageDTO;
import com.kata.bankaccount.exception.BankAccountException;
import com.kata.bankaccount.result.OutputDTO;
import com.kata.bankaccount.result.TypeMessage;
import com.kata.bankaccount.result.TypeReturn;

import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class BankAccountExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleUnknowException(final Exception ex, final WebRequest request) {
        log.error("handleUnknowException", ex);
        final OutputDTO sortie = new OutputDTO();
        MessageDTO message = new MessageDTO();
        message.setLibelleMessage("erreur");
        message.setTypeMessage(TypeMessage.ERREUR);
        sortie.addMessage(message);
        sortie.setCodeRetour(TypeReturn.KO);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sortie);
    }
    
    @ExceptionHandler({ BankAccountException.class })
    public ResponseEntity<Object> handleBankAccountException(final Exception ex, final WebRequest request) {
        log.error("bankAccountException", ex);
        final OutputDTO sortie = new OutputDTO();
        MessageDTO message = new MessageDTO();
        message.setLibelleMessage(ex.getMessage());
        message.setTypeMessage(TypeMessage.ERREUR);
        sortie.addMessage(message);
        sortie.setCodeRetour(TypeReturn.KO);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(sortie);
    }
}
