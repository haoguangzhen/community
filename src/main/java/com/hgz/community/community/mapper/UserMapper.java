package com.hgz.community.community.mapper;

import com.hgz.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO tables_USER (NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GET_MODIFIED) VALUES  (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    public void insert(User user);
}
//(NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GET_MODIFIED)