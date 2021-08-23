package DelvierySystem.service;

/*PackageService implements the required actions belonging to the PackageRepo interface*/

import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import DelvierySystem.dao.PackageRepo;
import DelvierySystem.model.Package;

@Service
public class PackageService implements PackageRepo{
	private final String PACKAGE_CACHE = "PAHCKAGES";

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, Package> hashOperations;
	
	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}
	@Override
	public Boolean save(Package package_) {
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
