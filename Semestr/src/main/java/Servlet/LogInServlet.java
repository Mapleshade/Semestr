package Servlet;

import Classes.User;
import Services.ServiceFactory;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/logIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password1");


        UserService userService = ServiceFactory.getUserService();
        if (login.equals("") || password.equals("") || !userService.isRegistered(login) || !userService.checkPassword(login, password)){
            resp.sendRedirect("/login");
        }else{
            Cookie cookie = userService.generateCookies();
            cookie.setMaxAge(60*60);
            User user = userService.getUser(login);
            userService.saveToken(user.getId(), cookie.getValue());
            resp.addCookie(cookie);
            req.getSession().setAttribute("current_user", req.getParameter("login"));
            resp.sendRedirect("/myPage");
        }
    }
}
