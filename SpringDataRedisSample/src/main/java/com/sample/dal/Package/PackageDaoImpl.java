package com.sample.dal.Package;

import java.util.LinkedList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.dal.deliveryMen.DeliveryMen;

@Repository
public class PackageDaoImpl implements PackageDao{
	  @Autowired
	    PackageRepository packageRepository;

	    @Autowired
	    RedisTemplate redisTemplate;

	    private static final String KEY = "package";
	@Override
	public Boolean savePackage(Package package_) {
		 try {
	            Map package_Hash  = new ObjectMapper().convertValue(package_, Map.class);
	            redisTemplate.opsForHash().put(KEY, Long.toString(package_.getId()), package_Hash);
	            return true;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	@Override
	public Package findPackageById(Long id) {
		 Map packageMap = (Map) redisTemplate.opsForHash().get(KEY, id);
	     Package package_ = new ObjectMapper().convertValue(packageMap, Package.class);
	     return package_;
	}
	@Override
	public State findStatePackage(Long id) {
		 Map packageMap = (Map) redisTemplate.opsForHash().get(KEY, id);
	     Package package_ = new ObjectMapper().convertValue(packageMap, Package.class);
	     return package_.getList_states().getLast();
	}

}