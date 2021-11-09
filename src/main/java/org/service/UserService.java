package org.service;


import jdk.nashorn.internal.runtime.Context;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.domian.User;
import org.persistence.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.util.GetSqlSession;
import org.util.GetSqlSessionFactory;


public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetSqlSessionFactory.class);

    public void register(User user){
        try {
            SqlSession sqlSession = GetSqlSession.getSqlSession();
            UserDAO mapper = sqlSession.getMapper(UserDAO.class);
            mapper.insertUser(user);
//            sqlSession.commit();
        } catch (Exception e) {
            LOGGER.error("insert error", e);
            GetSqlSession.rollback();
        } finally {
            GetSqlSession.commit();
            System.out.println("commit");
        }
    }

    public User getUserByEmail(String email)
    {
        User user = null;
        try {
            SqlSession sqlSession = GetSqlSession.getSqlSession();
            UserDAO mapper = sqlSession.getMapper(UserDAO.class);
            user = mapper.getUserByEmail(email);
        } catch (Exception e) {
            LOGGER.error("select error", e);
            GetSqlSession.rollback();
        } finally {
            GetSqlSession.commit();
        }
        return user;
    }

    public void deleteUserByEmail(String email){
        User user = getUserByEmail(email);
        System.out.println("delete function");
        System.out.println(user.toString());
        if (user != null) {
            try {
                System.out.println("deleting");
                SqlSession sqlSession = GetSqlSession.getSqlSession();
                UserDAO mapper = sqlSession.getMapper(UserDAO.class);
                mapper.deleteUserByEmail(email);
                LOGGER.info("user deleted");
            } catch (Exception e) {
                LOGGER.error("delete error", e);
                GetSqlSession.rollback();
            } finally {
                GetSqlSession.commit();
            }
        }
    }
}
