package org.jcg.springboot.redis.dao;

import java.util.Map;
import org.jcg.springboot.redis.model.Package;

public interface PackageRepo {
	
	// Save a new package.
	Boolean save(Package package_);
	
	// Find package by id.
	Package findById(String id);
		
	// Final all packages.
	Map<String, Package> findAll();
	
	// Delete a package.
	void delete(String id);
	
}
