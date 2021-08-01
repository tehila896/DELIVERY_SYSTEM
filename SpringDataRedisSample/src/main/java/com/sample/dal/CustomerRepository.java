package com.sample.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>  {

    public Optional<Customer> findById(Long id) ;
    public List<Customer> findByNameAndSurname(String name , String surname) ;



}
