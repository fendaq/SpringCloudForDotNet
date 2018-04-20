package top.gongtao.util;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/18 14:02
 * @Description: 操作 redis 工具类
 */
public class CacheUtils {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    private static CacheUtils cacheUtils;

    @PostConstruct
    public void init(){
        cacheUtils = this;
        cacheUtils.redisTemplate = this.redisTemplate;
    }


    /**
     * 将数据存入缓存
     * @param key
     * @param value
     */
    public static void saveString(String key,String value){
        ValueOperations<String,String> vo = cacheUtils.redisTemplate.opsForValue();
        vo.set(key,value);
    }


    /**
     * 将数据存入缓存的集合中
     * @param key
     * @param value
     */
    public static void saveToSet(String key,String value){
        SetOperations<String,String> so = cacheUtils.redisTemplate.opsForSet();
        so.add(key,value);
    }

    /**
     * 获取 set
     * @param key
     * @return
     */
    public static String getFromSet(String key){
        return cacheUtils.redisTemplate.opsForSet().pop(key);
    }


    /**
     * 将 key 的值保存为 value,当且仅当 key 不存在，若给定的 key 已经存在，则 SETNX 不做任何动作。
     * SETNX 是 set if not exists  如果不存在，则 SET
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean saveNX(String key,String value){
        return cacheUtils.redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.setNX(key.getBytes(),value.getBytes());
        });
    }

    /**
     * 将数据存入缓存，并设置失效时间
     * @param key
     * @param value
     * @param seconds
     */
    public static void saveString(String key, String value,int seconds){
        cacheUtils.redisTemplate.opsForValue().set(key,value,seconds,TimeUnit.SECONDS);
    }


    /**
     * 将自增变量存入缓存
     * @param key
     * @param seqNo
     */
    public static void saveSeq(String key,long seqNo){
        cacheUtils.redisTemplate.delete(key);
        cacheUtils.redisTemplate.opsForValue().increment(key,seqNo);
    }




}
