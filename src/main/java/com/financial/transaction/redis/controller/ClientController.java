package com.financial.transaction.redis.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financial.transaction.redis.dto.CheckBalanceAvailableDTO;
import com.financial.transaction.redis.dto.SenderDataTransaction;
import com.financial.transaction.redis.dto.business.ClientBusinessDTO;
import com.financial.transaction.redis.exceptions.FinancialTransactionManegementException;
import com.financial.transaction.redis.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private static final Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;
		
	@GetMapping("/checkBalanceAvailable")
	public CheckBalanceAvailableDTO checkBalanceAvailable(
			@RequestParam(name = "numberCard") String numberCard,
			@RequestParam(name = "password") String password, 
			@RequestParam BigDecimal value) throws FinancialTransactionManegementException {

    	log.info("Verificando disponibilidade no saldo.");

    	SenderDataTransaction senderDataTransaction = new SenderDataTransaction(numberCard, password, value);
    	
    	CheckBalanceAvailableDTO checkBalanceAvailableDTO = this.clientService.checkBalanceAvailable(senderDataTransaction);
		return checkBalanceAvailableDTO;
    }     	
	
	@PostMapping("/save")
	public ClientBusinessDTO save(@Valid @RequestBody ClientBusinessDTO clientBusinessDTO)
					throws FinancialTransactionManegementException {

    	log.info("Verificando disponibilidade no saldo.");

    	return this.clientService.saveRedis(clientBusinessDTO);
    }    	
	
	@GetMapping("/balance")
	public ClientBusinessDTO checkBalanceAvailableRedis(
			@RequestParam(name = "numberCard") String numberCard) throws FinancialTransactionManegementException {

    	log.info("Verificando disponibilidade no saldo.");
    	
    	ClientBusinessDTO clientBusinessDTO = this.clientService.checkBalanceAvailableRedis(numberCard);
    	return clientBusinessDTO;
    }     	

}
