/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOSupplier;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerSupplier", urlPatterns = {"/ControllerSupplier"})
public class ControllerSupplier extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerSupplier</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerSupplier at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
//        processRequest(request, response);
            DAOSupplier dao = new DAOSupplier();
            //get data
            String companyName = request.getParameter("companyName");
            String contactName = request.getParameter("contactName");
            String contactTitle = request.getParameter("contactTitle");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String region = request.getParameter("region");
            String postalCode = request.getParameter("postalCode");
            String country = request.getParameter("country");
            String phone = request.getParameter("phone");
            String fax = request.getParameter("fax");
            String homePage = request.getParameter("homePage");
            //check  data
            String checkNum = "[0-9-]+";
            if (companyName.isEmpty()) {
                out.print("companyName is empty");
//                response.sendRedirect("addSupplier.html");
                return;
            }
            
            if (contactName.isEmpty()) {
                out.print("contactName is empty");
                return;
            }
            
            if (contactTitle.isEmpty()) {
                out.print("contactTitle is empty");
                return;
            }
            
            if (address.isEmpty()) {
                out.print("address is empty");
                return;
            }
            
            if (city.isEmpty()) {
                out.print("city is empty");
                return;
            }
            
            if (region.isEmpty()) {
                out.print("region is empty");
                return;
            }
            
            if(!postalCode.matches(checkNum)) {
                out.print("postalCode is invalid");
                return;
            }
            
            if (country.isEmpty()) {
                out.print("country is empty");
                return;
            }
            
            if(!phone.matches(checkNum)) {
                out.print("phone is invalid");
                return;
            }
            
            if(!fax.matches(checkNum)) {
                out.print("fax is invalid");
                return;
            }
            
            if (homePage.isEmpty()) {
                out.print("homePage is empty");
                return;
            }
            //convert
            //add db
            Supplier sup = new Supplier(companyName, contactName, contactTitle, address,
                    city, region, postalCode, country, phone, fax, homePage);
            int n = dao.addSupplier(sup);
            if(n > 0) {
                out.print("Insert successful");
            }
            else {
                out.print("Insert fail");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
