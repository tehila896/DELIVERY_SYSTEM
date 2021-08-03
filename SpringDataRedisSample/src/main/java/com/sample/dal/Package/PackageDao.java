package com.sample.dal.Package;

public interface PackageDao {
	  
	    public Boolean savePackage(Package package_) ;
	    public Package findPackageById(Long id) ;
	    public State findStatePackage(Long id) ;
	    public State updateStatePackage(Long id,State state) ;

}
