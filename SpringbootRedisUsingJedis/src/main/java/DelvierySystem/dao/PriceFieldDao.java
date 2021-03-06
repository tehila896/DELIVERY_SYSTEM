package DelvierySystem.dao;

/*PriceFieldDao defines the actions we perform in front of the redis database*/
import java.util.Map;

import DelvierySystem.model.PriceField;
import DelvierySystem.model.UnitOfDistance;

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
