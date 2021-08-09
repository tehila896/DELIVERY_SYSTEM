package org.jcg.springboot.redis.service;

import java.util.Map;
import javax.annotation.PostConstruct;
import org.jcg.springboot.redis.dao.PackageRepo;
import org.jcg.springboot.redis.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PackageService implements PackageRepo{
	private final String PACKAGE_CACHE = "PACKAGES";

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, Package> hashOperations;
	
	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}
	@Override
	public Boolean save(final Package package_) {
		 try {
			 hashOperations.put(PACKAGE_CACHE, package_.getId(), package_);
	         return true;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }		
	}
	@Override
	public Package findById(String id) {
		return (Package) hashOperations.get(PACKAGE_CACHE, id);
	}
	@Override
	public Map<String, Package> findAll() {
		return hashOperations.entries(PACKAGE_CACHE);
	}
	@Override
	public void delete(String id) {
		hashOperations.delete(PACKAGE_CACHE, id);
	}
}
