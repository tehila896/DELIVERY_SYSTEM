package org.jcg.springboot.redis.service;


import java.util.Map;
import javax.annotation.PostConstruct;
import org.jcg.springboot.redis.dao.PriceFieldDao;
import org.jcg.springboot.redis.model.PriceField;
import org.jcg.springboot.redis.model.UnitOfDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PriceFieldService implements PriceFieldDao{
	private final String PRICEFIELD_CACHE = "PSSRICEFIELDDDDDDDD";
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String,String,PriceField> hashOperations;
	
	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}
	@Override
	public Boolean save(PriceField priceField) {
		 try {
			 hashOperations.put(PRICEFIELD_CACHE, priceField.getRow().toString(),priceField);
	         return true;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}

	@Override
	public PriceField findById(UnitOfDistance id) {
		return (PriceField) hashOperations.get(PRICEFIELD_CACHE, id.toString());
	}

	@Override
	public Map<String, PriceField> findAll() {
		return hashOperations.entries(PRICEFIELD_CACHE);
	}

	@Override
	public void delete(UnitOfDistance id) {
		hashOperations.delete(PRICEFIELD_CACHE, id);
	}

}
