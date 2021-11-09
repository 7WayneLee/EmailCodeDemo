package org.service;

import org.apache.ibatis.session.SqlSession;
import org.domian.Registered;
import org.domian.User;
import org.persistence.RegisteredDAO;
import org.persistence.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.util.GetSqlSession;
import org.util.GetSqlSessionFactory;

public class RegisteredService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetSqlSessionFactory.class);
    public void add(String email){
        Registered registered = new Registered(email);
        try {
            SqlSession sqlSession = GetSqlSession.getSqlSession();
            RegisteredDAO mapper = sqlSession.getMapper(RegisteredDAO.class);
            mapper.insertUser(registered);
        } catch (Exception e) {
            LOGGER.error("select error", e);
            GetSqlSession.rollback();
        } finally {
            GetSqlSession.commit();
        }
    }

    public Registered checkEmail(String email) {
        Registered registered = null;
        try {
            SqlSession sqlSession = GetSqlSession.getSqlSession();
            RegisteredDAO mapper = sqlSession.getMapper(RegisteredDAO.class);
            registered = mapper.checkEmail(email);
        } catch (Exception e) {
            LOGGER.error("select error", e);
            GetSqlSession.rollback();
        } finally {
            GetSqlSession.commit();
        }
        return registered;
    }
}
