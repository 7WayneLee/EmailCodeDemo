package org.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.domian.Registered;


@Mapper
public interface RegisteredDAO {
    @Insert("insert into Registered(email) values(#{email})")
    void insertUser(Registered registered);

    @Select("select * from Registered where email = #{email}")
    Registered checkEmail(String email);
}
