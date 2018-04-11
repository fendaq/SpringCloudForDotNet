package top.gongtao.conf;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/11 09:28
 * @Description:
 */
public class MySQL5DialectUTF8 extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
