package Servlet;

import Services.ServiceFactory;
import Services.UniversityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpecServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UniversityService serv = ServiceFactory.getUniversityService();
        if(req.getParameter("id") != null && serv.getAllUnivWithSpec(Integer.parseInt(req.getParameter("id"))) != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("spec", serv.getSpeciality(id));
            req.setAttribute("univs", serv.getAllUnivWithSpec(id));
            req.getRequestDispatcher("/speciality.jsp").forward(req, resp);
        } else {
            req.setAttribute("specs", serv.getAllSpeialities());
            req.getRequestDispatcher("/specialities.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
