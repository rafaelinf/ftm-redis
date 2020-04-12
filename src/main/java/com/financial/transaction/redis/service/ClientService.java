package com.financial.transaction.redis.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.financial.transaction.redis.dto.CheckBalanceAvailableDTO;
import com.financial.transaction.redis.dto.SenderDataTransaction;
import com.financial.transaction.redis.dto.business.ClientBusinessDTO;
import com.financial.transaction.redis.exceptions.FinancialTransactionManegementException;
import com.financial.transaction.redis.rest.integration.BusinessRestIntegration;

@Service
public class ClientService {
	
	private static final Logger log = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	private BusinessRestIntegration businessRestIntegration;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

    public CheckBalanceAvailableDTO checkBalanceAvailable (SenderDataTransaction senderDataTransaction) throws FinancialTransactionManegementException {
    	
    	log.info("Verificando disponibilidade no saldo.");

    	ClientBusinessDTO clientBusinessDTO = this.checkBalanceAvailableRedis(senderDataTransaction.getNumberCard());   	
    	if(clientBusinessDTO == null) {    		  		
    		
    		clientBusinessDTO = this.checkBalanceAvailableIntegration(senderDataTransaction);
    		if(clientBusinessDTO == null) {
        		log.info("O cartão não foi encontrado ou não está ativo");
    			throw new FinancialTransactionManegementException("O cartão não foi encontrado ou não está ativo");
    		}
   		
    	}
    	
		if(clientBusinessDTO.getBalance().doubleValue() > senderDataTransaction.getValue().doubleValue()) {
			return new CheckBalanceAvailableDTO(clientBusinessDTO.getCpf(), senderDataTransaction.getNumberCard(), true);
		}else {
			log.info("O saldo é insuficiente");
			throw new FinancialTransactionManegementException("O saldo é insuficiente");
		}    	

    }
    
    public ClientBusinessDTO checkBalanceAvailableRedis (String numberCard) throws FinancialTransactionManegementException {    	
    	log.info("Buscando dados do cliente no redis.");

    	ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
    	return (ClientBusinessDTO) valueOperations.get(numberCard);
    }
    
    public ClientBusinessDTO checkBalanceAvailableIntegration (SenderDataTransaction senderDataTransaction) throws FinancialTransactionManegementException {
    	log.info("Buscando dados do cliente no cadastro.");
    	
    	ClientBusinessDTO clientBusinessDTO = businessRestIntegration.findClientByNumberCard(senderDataTransaction.getNumberCard());
    	if(clientBusinessDTO != null) {
    		clientBusinessDTO = this.saveRedis(clientBusinessDTO);
    		return clientBusinessDTO;
    	}
    	
    	return null;
    	
    }    
        
    public ClientBusinessDTO saveRedis(ClientBusinessDTO clientBusinessDTO) throws FinancialTransactionManegementException {
    	log.info("Salvando dados do cliente no redis.");
    	
    	if(clientBusinessDTO != null) {

        	ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
        	valueOperations.set(clientBusinessDTO.getNumberCard(), clientBusinessDTO);
    		log.info("Dados do cliente salvo com sucesso no redis.");
    		
    		return clientBusinessDTO;
    		
    	}else {
			log.error("O cliente não foi encontrado.");   
			throw new FinancialTransactionManegementException("O cliente não foi encontrado.");
    	}
    	
    }
       
}