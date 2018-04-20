package top.gongtao.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.gongtao.util.CacheUtils;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/18 14:29
 * @Description:
 */

@Configuration
@Import({RedisConfig.class,CacheUtils.class})
public class RedisAutoConfiguration {
}
