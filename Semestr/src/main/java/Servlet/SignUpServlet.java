package Servlet;

import Classes.User;
import Services.ServiceFactory;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = ServiceFactory.getUserService();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String login = req.getParameter("login");
        String password = req.getParameter("password1");
        String passConf = req.getParameter("password2");

        if (login.equals("") || password.equals("") || passConf.equals("") || !password.equals(passConf) || userService.isRegistered(login)){
            resp.sendRedirect("/registration");
        }else {
            User user = new User(0,name,surname,country,city,login,password);
            if (userService.saveUser(user)) {
                resp.sendRedirect("/login");
            } else {
                resp.sendRedirect("/registration");
            }
        }
        }
    }
