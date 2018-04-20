package top.gongtao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import top.gongtao.util.CacheUtils;

import javax.annotation.Resource;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AntdServerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class EurekaServiceApplicationTests {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void shouldStartEurekaServer() {

    }

    @Test
    public void testRedis(){
        CacheUtils.saveString("haha","haha");

        ValueOperations<String,String> vol = redisTemplate.opsForValue();

        System.out.println(vol.get("name"));
    }


}
