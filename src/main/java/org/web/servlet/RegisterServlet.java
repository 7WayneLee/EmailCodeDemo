package org.web.servlet;

import org.apache.ibatis.session.SqlSession;
import org.domian.User;
import org.persistence.UserDAO;
import org.service.RegisteredService;
import org.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.util.GetSqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    private RegisteredService registeredService = new RegisteredService();
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("useremail");
        String code = req.getParameter("emailcode");
        //System.out.println("service start");
//        userService.register(user);
        //System.out.println("service end");
        User user = userService.getUserByEmail(email);

        if (user.getCode().equals(code)){
            userService.deleteUserByEmail(email);
            registeredService.add(email);
            req.getRequestDispatcher("WEB-INF/jsp/registerSuccess.jsp").forward(req,resp);
        } else {
            String message = "Wrong code!";
            req.setAttribute("email", email);
            req.setAttribute("message", message);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = userService.getUserByEmail(email);
    }


}
