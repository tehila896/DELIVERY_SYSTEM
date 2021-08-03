package com.sample.dal.Package;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PackageRepository extends CrudRepository<Package, Long>{
	 public Optional<Package> findById(Long id) ;
	  
}
