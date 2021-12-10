package com.originsys.im.dao;

import com.originsys.im.domain.EimUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EimUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EimUser record);

    int insertSelective(EimUser record);

    EimUser selectByPrimaryKey(Long id);

    EimUser selectByUserData(EimUser eimUser);

    int updateByPrimaryKeySelective(EimUser record);

    int updateByPrimaryKey(EimUser record);
}