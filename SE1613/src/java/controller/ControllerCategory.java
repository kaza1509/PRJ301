/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCategory;
import entity.Category;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerCategory", urlPatterns = {"/ControllerCategory"})
public class ControllerCategory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerCategory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOCategory dao = new DAOCategory();
            //get data
            String categoryName = request.getParameter("categoryName");
            String description = request.getParameter("description");
            String picture = request.getParameter("picture");
            //check data
            if (categoryName.isEmpty()) {
                out.print("categoryName is empty");
                return;
            }

            if (description.isEmpty()) {
                out.print("description is empty");
                return;
            }

            if (picture.isEmpty()) {
                out.print("picture is empty");
                return;
            }
            //convert
            //add db
            Category cate = new Category(categoryName, description);
            int n = dao.addCategory(cate);
            if (n > 0) {
                out.print("Inserted");
            } else {
                out.print("Error");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
