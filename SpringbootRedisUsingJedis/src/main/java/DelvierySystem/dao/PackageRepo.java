package DelvierySystem.dao;

/*PackageRepo defines the actions we perform in front of the redis database*/

import java.util.Map;

import DelvierySystem.model.Package;

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
