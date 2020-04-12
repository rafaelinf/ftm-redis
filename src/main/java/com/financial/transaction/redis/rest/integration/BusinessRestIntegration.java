package com.financial.transaction.redis.rest.integration;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.financial.transaction.redis.dto.business.ClientBusinessDTO;
import com.financial.transaction.redis.exceptions.FinancialTransactionManegementException;

@Component
public class BusinessRestIntegration {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessRestIntegration.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${url-base.business}")
	private String urlBase;

	public List<ClientBusinessDTO> findClients() throws FinancialTransactionManegementException {
		try {

			LOGGER.info("Buscar clientes");

			String url = urlBase + "clients/all";
			ResponseEntity<ClientBusinessDTO[]> response = restTemplate.getForEntity(url, ClientBusinessDTO[].class);
			
			ClientBusinessDTO[] listClients = response.getBody();
			return Arrays.asList(listClients);

		} catch (HttpClientErrorException e) {
			LOGGER.error("Erro ao buscar Response {}", e.getResponseBodyAsString(), e);
			throw new FinancialTransactionManegementException("Ocorreu um erro na chamada do endpoint do projeto business");
		}
	}	
	
	public ClientBusinessDTO findClientByNumberCard(String numberCard) throws FinancialTransactionManegementException {
		try {

			LOGGER.info("Buscar cliente por numberCard");

			String url = urlBase + "clients/numberCard/" + numberCard;
			ClientBusinessDTO clientBusinessDTO = restTemplate.getForObject(url, ClientBusinessDTO.class);
			
			return clientBusinessDTO;

		} catch (HttpClientErrorException e) {
			LOGGER.error("Erro ao buscar Response {}", e.getResponseBodyAsString(), e);
			throw new FinancialTransactionManegementException("Ocorreu um erro na chamada do endpoint do projeto business");
		}
	}		

}
