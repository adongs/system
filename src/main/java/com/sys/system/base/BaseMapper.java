package com.sys.system.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用的mapper 这个借口不能被扫描到
 * @Author yudong
 * @Date 2018年05月02日 下午2:29
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
