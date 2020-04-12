package com.financial.transaction.redis.repository;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.financial.transaction.redis.dto.business.ClientBusinessDTO;

@Repository
public class ClientRepository {

    private HashOperations hashOperations;

	private RedisTemplate redisTemplate;

	public ClientRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public void save(ClientBusinessDTO client) {
		hashOperations.put("Client", client.getId(), client);
	}

	public List<ClientBusinessDTO> findAll() {
		return hashOperations.values("Client");
	}

	public ClientBusinessDTO findById(String id) {
		return (ClientBusinessDTO) hashOperations.get("Client", id);
	}
	
	public ClientBusinessDTO findByNumberCard(String numberCard) {
		return (ClientBusinessDTO) hashOperations.get("Client", numberCard);
	}	

	public void update(ClientBusinessDTO client) {
		save(client);
	}

	public void delete(String id) {
		hashOperations.delete("Client", id);
	}
//	ClientBusinessDTO findByCpf(String cpf);
//	
//	ClientBusinessDTO findByNumberCard(String numberCard);

}