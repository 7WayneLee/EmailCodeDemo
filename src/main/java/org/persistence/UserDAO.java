package org.persistence;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.domian.User;

@Mapper
public interface UserDAO {
    @Insert("insert into User(email, code) values(#{email}, #{code})")
    void insertUser(User user);

    @Select("select * from User where email = #{email}")
    User getUserByEmail(String email);

    @Delete("delete from User where email = #{email}")
    void deleteUserByEmail(String email);
}
