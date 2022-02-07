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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            DAOCategory dao = new DAOCategory();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllCategory";
            }
            if (service.equals("InsertCategory")) {
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
                response.sendRedirect("ControllerCategory");
            } else if (service.equals("listAllCategory")) {
                Vector<Category> vector = dao.listAll("select * from Categories");
                out.print("<p><a href=\"addCategory.html\">Add Category</a></p>");
                out.print("<table border=\"1\">"
                        + "<caption>List of Categories</caption>"
                        + "        <thead>"
                        + "            <tr>"
                        + "                <th>CategoryID</th>"
                        + "                <th>CategoryName</th>"
                        + "                <th>Description</th>"
                        + "                <th>Picture</th>"
                        + "                <th>Update</th>"
                        + "                <th>Delete</th>"
                        + "            </tr>"
                        + "        </thead>"
                        + "        <tbody>");
                for (Category cate : vector) {
                    out.print("            <tr>"
                            + "                <td>" + cate.getCategoryId() + "</td>"
                            + "                <td>" + cate.getCategoryName() + "</td>"
                            + "                <td>" + cate.getDescription() + "</td>"
                            + "                <td>Picture</td>"
                            //                            + "                <td>" + cate.getPicture() + "</td>"
                            + "                <td><a href=\"ControllerCategory?do=updateCategory&categoryId=" + cate.getCategoryId() + "\">update</a></td>"
                            + "                <td><a href=\"ControllerCategory?do=deleteCategory&categoryId=" + cate.getCategoryId() + "\">delete</a></td>"
                            + "            </tr>");
                }
                out.print(" </tbody>"
                        + "    </table>");
            } else if (service.equals("updateCategory")) {
                String submit = request.getParameter("submit");
                String categoryId = request.getParameter("categoryId");
                if (submit == null) {
                    ResultSet rs = dao.getData("select * from Categories where categoryID = " + categoryId);
                    if (rs.next()) {
                        out.print("<form action=\"ControllerCategory?do=updateCategory\" method=\"post\">"
                                + "            <table>"
                                + "                <tr>"
                                + "                    <h1>Update category</h1>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>CategoryID</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"categoryId\" value=\"" + rs.getString(1) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>CategoryName</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"categoryName\" value=\"" + rs.getString(2) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Description</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"description\" value=\"" + rs.getString(3) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Picture</td>"
                                + "                    <td>"
                                + "                        <input type=\"file\" name=\"picture\" value=\"" + rs.getString(4) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>"
                                + "                        <input type=\"submit\" value=\"Update\" name=\"submit\">"
                                + "                    </td>"
                                + "                    <td>"
                                + "                        <input type=\"reset\" value=\"Clear\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "            </table>"
                                + "        </form>");
                    }
                } else {
                    int cateId = Integer.parseInt(request.getParameter("categoryId"));
                    String categoryName = request.getParameter("categoryName");
                    String description = request.getParameter("description");
                    Category cate = new Category(cateId, categoryName, description, "picture");
                    int n = dao.updateCategory(cate);
                    response.sendRedirect("ControllerCategory");
                }
            } else if (service.equals("deleteCategory")) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCategory.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
