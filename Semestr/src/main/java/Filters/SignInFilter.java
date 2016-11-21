package Filters;

import Classes.User;
import DAO.UsersDAO;
import Services.ServiceFactory;
import Services.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SignInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(((HttpServletRequest)servletRequest).getSession().getAttribute("current_user") != null) {
            servletRequest.getRequestDispatcher("/myPage").forward(servletRequest, servletResponse);
        } else {
            UserService serv = ServiceFactory.getUserService();
            String token = null;
            Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals("MSiteCookie")) {
                    token = cookie.getValue();
                }
            }

            if (token != null) {
                User user = serv.isExistToken(token);
                if (user != null) {
                    ((HttpServletRequest) servletRequest).getSession().setAttribute("current_user", user.getLogin());
                    servletRequest.getRequestDispatcher("/myPage").forward(servletRequest, servletResponse);
                } else {
                    servletRequest.getRequestDispatcher("/registration").forward(servletRequest, servletResponse);
                }
            } else {
                servletRequest.getRequestDispatcher("/registration").forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
