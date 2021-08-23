package org.jcg.springboot.redis.dao;

import java.util.Map;
import org.jcg.springboot.redis.model.PriceField;
import org.jcg.springboot.redis.model.UnitOfDistance;

public interface PriceFieldDao {
	
	   // Save a new PriceField.
		Boolean save(PriceField priceField);
		
		// Find PriceField by id.
		PriceField findById(UnitOfDistance id);
			
		// Final all PriceFields.
		Map<String, PriceField> findAll();
		
		// Delete a PriceField.
		void delete(UnitOfDistance id);
}
