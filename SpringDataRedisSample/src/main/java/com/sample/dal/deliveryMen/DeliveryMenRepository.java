package com.sample.dal.deliveryMen;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMenRepository  extends CrudRepository<DeliveryMen,Long>{
	public Optional<DeliveryMen> findById(Long id) ;
}
