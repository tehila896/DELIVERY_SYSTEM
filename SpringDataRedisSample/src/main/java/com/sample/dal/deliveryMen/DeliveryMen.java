package com.sample.dal.deliveryMen;

import java.awt.Point;
import org.springframework.data.redis.core.RedisHash;
import lombok.Data;

@Data
@RedisHash("DeliveryMen")
public class DeliveryMen {
Long id;
String contex_way;
String getSalary_way;
Point position;
boolean statosCurrentlyWorking;
}
