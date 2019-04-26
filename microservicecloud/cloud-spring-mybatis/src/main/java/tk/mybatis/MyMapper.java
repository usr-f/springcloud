package tk.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by Administrator on 2019/4/24 0024.
 * 该接口不能被扫描到,否则会报错
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
