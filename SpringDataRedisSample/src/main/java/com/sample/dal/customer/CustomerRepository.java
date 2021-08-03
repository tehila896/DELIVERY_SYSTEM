package com.sample.dal.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>  {

   public Optional<Customer> findById(Long id) ;
   }
