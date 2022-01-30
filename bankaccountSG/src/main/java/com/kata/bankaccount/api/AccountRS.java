package com.kata.bankaccount.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bankaccount.entite.OperationEnum;
import com.kata.bankaccount.exception.BankAccountException;
import com.kata.bankaccount.result.AccountOuput;
import com.kata.bankaccount.result.OutputDTO;
import com.kata.bankaccount.result.TypeReturn;
import com.kata.bankaccount.services.IAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = { "01 - Account" })
@RequestMapping("/api/account")
public class AccountRS {

    @Autowired
    private IAccountService accountService;
    
    @ApiOperation(value = "Gets the transaction list of an account")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = AccountOuput.class),
        @ApiResponse(code = 400, message = "Bad Request", response = OutputDTO.class),
        @ApiResponse(code = 403,
            message = "Forbidden ",
            response = OutputDTO.class),
        @ApiResponse(code = 404, message = "Not Found",
            response = OutputDTO.class),
        @ApiResponse(code = 500, message = "Server error", response = OutputDTO.class) })
    @GetMapping(value = "/transactionslist/{idAccount}")
    public ResponseEntity<AccountOuput> getListTransactions(@ApiParam(value = "Id account", defaultValue = "0",
            required = true) @PathVariable("idAccount") final long idAccount) {
        final AccountOuput accountOutput = new AccountOuput();
        accountOutput.setResultat(accountService.getAccount(idAccount));
        accountOutput.setCodeRetour(TypeReturn.OK);
        return ResponseEntity.status(200).body(accountOutput);
    }
    
    @ApiOperation(value = "Make deposit into an account")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = AccountOuput.class),
        @ApiResponse(code = 400, message = "Bad Request", response = OutputDTO.class),
        @ApiResponse(code = 403,
            message = "Forbidden",
            response = OutputDTO.class),
        @ApiResponse(code = 404, message = "Not Found",
            response = OutputDTO.class),
        @ApiResponse(code = 500, message = "Server error", response = OutputDTO.class) })
    @PostMapping(value = "/{idAccount}/makedeposit/{amount}")
    public ResponseEntity<AccountOuput> makeDesposit(@ApiParam(value = "Id account", defaultValue = "0",
            required = true) @PathVariable("idAccount") final long idAccount, @ApiParam(value = "Amount", defaultValue = "0",
            required = true) @PathVariable("amount") final long amount) throws BankAccountException {
        final AccountOuput accountOutput = new AccountOuput();
        accountOutput.setResultat(accountService.makeOperation(idAccount, amount, OperationEnum.DEPOSIT));
        accountOutput.setCodeRetour(TypeReturn.OK);
        return ResponseEntity.status(200).body(accountOutput);
    }
    
    @ApiOperation(value = "Make withdrawal into an account")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = AccountOuput.class),
        @ApiResponse(code = 400, message = "Bad Request", response = OutputDTO.class),
        @ApiResponse(code = 403,
            message = "Forbidden",
            response = OutputDTO.class),
        @ApiResponse(code = 404, message = "Not Found",
            response = OutputDTO.class),
        @ApiResponse(code = 500, message = "Server error", response = OutputDTO.class) })
    @PostMapping(value = "/{idAccount}/makedwithdrawal/{amount}")
    public ResponseEntity<AccountOuput> makeWithdrawal(@ApiParam(value = "Id account", defaultValue = "0",
            required = true) @PathVariable("idAccount") final long idAccount, @ApiParam(value = "Amount", defaultValue = "0",
            required = true) @PathVariable("amount") final long amount) throws BankAccountException {
        final AccountOuput accountOutput = new AccountOuput();
        accountOutput.setResultat(accountService.makeOperation(idAccount, amount, OperationEnum.WITHDRAWAL));
        accountOutput.setCodeRetour(TypeReturn.OK);
        return ResponseEntity.status(200).body(accountOutput);
    }
}
