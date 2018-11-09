package com.chris.demo.core.auto.mapper;

import com.chris.demo.core.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig
public interface UserMapper {
    @CacheEvict
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    @Cacheable(key="#p0")
    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @CachePut(key ="#p0")
    @Update("update user set name=#{name} where id=#{id}")
    void updataById(@Param("id") String id, @Param("name") String name);
}
