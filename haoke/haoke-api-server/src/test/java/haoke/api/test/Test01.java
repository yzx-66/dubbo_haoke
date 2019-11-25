package haoke.api.test;

import haoke.server.houseResources.api.ApiHouseResource;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test01 {

    @Reference(version = "1.0.0")
    private ApiHouseResource apiHouseResource;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test01(){
        System.out.println(apiHouseResource.insertHouseResource(null));
    }

    @Test
    public void test02(){
        redisTemplate.opsForValue().set("test10_21","10_21");
    }
}
