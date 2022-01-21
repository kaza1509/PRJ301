package controller;

import entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOOrder;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerOrder", urlPatterns = {"/ControllerOrder"})
public class ControllerOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerOrder at " + request.getContextPath() + "</h1>");
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
            DAOOrder dao = new DAOOrder();
            //get data
            String customerID = request.getParameter("customerId");
            String employeeId = request.getParameter("employeeId");
            String orderDate = request.getParameter("orderDate");
            String requiredDate = request.getParameter("requiredDate");
            String shippedDate = request.getParameter("shippedDate");
            String shipVia = request.getParameter("shipVia");
            String freight = request.getParameter("freight");
            String shipName = request.getParameter("shipName");
            String shipAddress = request.getParameter("shipAddress");
            String shipCity = request.getParameter("shipCity");
            String shipRegion = request.getParameter("shipRegion");
            String shipPostalCode = request.getParameter("shipPostalCode");
            String shipCountry = request.getParameter("shipCountry");

            //check data
            String checkInt = "[1-9][0-9]*";
            String checkDob = "[0-9]+[.][0-9]+";
            String checkShipCode = "[0-9-]+";
            
            LocalDate orderDateValue = LocalDate.parse(orderDate);
            LocalDate requiredDateValue = LocalDate.parse(requiredDate);
            LocalDate shippedDateValue = LocalDate.parse(shippedDate);

            if (!orderDateValue.isBefore(shippedDateValue)) {
                out.print("Orderdate < shipDate");
                return;
            }
            if (!shippedDateValue.isBefore(requiredDateValue)) {
                out.print("shippedDate < requiredDate");
                return;
            }
            if(!freight.matches(checkDob)) {
                out.print("Freight is number");
                return;
            }
            if(shipName.isEmpty()) {
                out.print("ShipName is empty");
                return;
            }
            if(shipAddress.isEmpty()) {
                out.print("shipAddress is empty");
                return;
            }
            if(shipCity.isEmpty()) {
                out.print("shipCity is empty");
                return;
            }
            if(shipRegion.isEmpty()) {
                out.print("shipRegion is empty");
                return;
            }
            if(!shipPostalCode.matches(checkShipCode)) {
                out.print("shipPostalCode is invalid");
                return;
            }
            if(shipCountry.isEmpty()) {
                out.print("shipCountry is empty");
                return;
            }
             //convert data
            double freightValue = Double.parseDouble(freight);
            int employeeIdValue = Integer.parseInt(employeeId);
            //add db
            Order od = new Order(customerID, employeeIdValue, String.valueOf(orderDateValue), 
                    String.valueOf(requiredDateValue),String.valueOf(shippedDateValue), 
                    Integer.parseInt(shipVia), freightValue, shipName, shipAddress, shipCity,
                    shipRegion, shipPostalCode, shipCountry);
            int n = dao.addOrder(od);
            if(n > 0) {
                out.println("Inserted successful");
            }
            else {
                out.println("Inserted fail");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
