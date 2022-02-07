/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.SaleDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOSaleDetail;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerSaleDetail", urlPatterns = {"/controllerSaleDetail"})
public class ControllerSaleDetail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("do");
            if(service == null) {
                service = "list";
            }
            if (service.equals("list")) {
                DAOSaleDetail dao = new DAOSaleDetail();
                Vector<SaleDetail> list = dao.getAll();
                out.print("<table border=\"1\">"
                        + "        <thead>"
                        + "            <tr>"
                        + "                <th>OrderID</th>"
                        + "                <th>ProductID</th>"
                        + "                <th>UnitPrice</th>"
                        + "                <th>Quantity</th>"
                        + "                <th>Discount</th>"
                        + "                <th>CategoryID</th>"
                        + "                <th>CategoryName</th>"
                        + "                <th>ProductName</th>"
                        + "                <th>CustomerID</th>"
                        + "                <th>CompanyName</th>"
                        + "            </tr>"
                        + "        </thead>"
                        + "        <tbody>");
                
                for (SaleDetail s : list) {
                    out.print("<tr>"
                            + "                <td>"+s.getOrderId()+"</td>"
                            + "                <td>"+s.getProductId()+"</td>"
                            + "                <td>"+s.getUnitPrice()+"</td>"
                            + "                <td>"+s.getQuantity()+"</td>"
                            + "                <td>"+s.getDiscount()+"</td>"
                            + "                <td>"+s.getCategoryId()+"</td>"
                            + "                <td>"+s.getCategoryName()+"</td>"
                            + "                <td>"+s.getProductName()+"</td>"
                            + "                <td>"+s.getCustomerId()+"</td>"
                            + "                <td>"+s.getCompanyName()+"</td>"
                            + "</tr>");
                }
                
                out.print("</tbody>"
                        + "    </table>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
