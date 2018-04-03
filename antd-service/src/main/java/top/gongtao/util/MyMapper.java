package top.gongtao.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/3 10:57
 * @Description:
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}

