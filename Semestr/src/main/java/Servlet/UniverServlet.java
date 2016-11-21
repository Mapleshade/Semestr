package Servlet;

import Services.ServiceFactory;
import Services.UniversityService;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UniverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UniversityService serv = ServiceFactory.getUniversityService();
        if(req.getParameter("id") != null && serv.getUniversity(Integer.parseInt(req.getParameter("id")), false) != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("univ", serv.getUniversity(id, true));
            UserService uServ = ServiceFactory.getUserService();
            if (req.getSession().getAttribute("current_user") != null && uServ.getUser((String)req.getSession().getAttribute("current_user"))!=null) {
                req.setAttribute("userS", uServ);
                req.setAttribute("user",uServ.getUser((String)req.getSession().getAttribute("current_user")) );
            }
            req.getRequestDispatcher("/university.jsp").forward(req, resp);
        } else {
            req.setAttribute("univs", serv.getAllUniviversities());
            req.getRequestDispatcher("/universities.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
