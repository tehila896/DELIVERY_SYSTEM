package com.sample.dal.deliveryMen;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.dal.Package.State;
import com.sample.dal.customer.Customer;


@Repository
public class DeliveryMenDaoImpl implements DeliveryMenDao{
	@Autowired
    DeliveryMenRepository deliveryMenRepository;

    @Autowired
    RedisTemplate redisTemplate;

    private static final String KEY = "deliveryMen";
    @Override
    public Boolean saveDeliveryMen(DeliveryMen deliveryMen) {
        try {
            Map customerHash  = new ObjectMapper().convertValue(deliveryMen, Map.class);
            redisTemplate.opsForHash().put(KEY, Long.toString(deliveryMen.getId()), customerHash);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public DeliveryMen findDeliveryMenById(Long id) {
        Map deliveryMenMap = (Map) redisTemplate.opsForHash().get(KEY, id);
        DeliveryMen deliveryMen = new ObjectMapper().convertValue(deliveryMenMap, DeliveryMen.class);
        return deliveryMen;
    }
}
