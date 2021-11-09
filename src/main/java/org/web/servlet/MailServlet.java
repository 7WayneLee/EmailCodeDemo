package org.web.servlet;

import org.domian.Registered;
import org.domian.User;
import org.service.RegisteredService;
import org.service.UserService;
import org.util.EmailSender;
import org.util.RandomCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "MailServlet", value = "/mail")
public class MailServlet extends HttpServlet {
    private UserService userService = new UserService();
    private RegisteredService registeredService = new RegisteredService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        String email = req.getParameter("email");
        Registered registered = registeredService.checkEmail(email);
        if (registered == null) {
            String emailCode = RandomCode.getCode();
            User user = userService.getUserByEmail(email);
            // prevent repeat registering
            if (user == null) {
                user = new User(email, emailCode);
                userService.register(user);
                EmailSender.send(email, emailCode);
            }
            pw.println(1);
        } else {
            // if user had registered
            pw.println(2);
        }
//        System.out.println("mail post");
//        System.out.println(email);

        pw.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("1");
        pw.close();
    }


}
