/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDAO;
import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DTO.CartDTO;
import DTO.FoodDTO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class UserCheckOutController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String SUCCESS = "index.jsp";

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
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("ROLE");
            if (role == null) {
                session.setAttribute("CONTINUE", "Login to continue");
                url = LOGIN;
            } else if (role.equals("admin")) {
                url = ERROR;
                request.setAttribute("ERROR", "Your role not allow to use this funtion");
            } else {
                CartDTO shoppingCart = (CartDTO) session.getAttribute("CART");
                if (shoppingCart.getTotal() != 0) {
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    Date today = new Date();
                    String checkOutDate = ft.format(today);
                    String orderID;
                    OrderDAO orderDAO = new OrderDAO();
                    AccountDAO accountDAO = new AccountDAO();
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    boolean check = true;
                    do {
                        orderID = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
                        System.out.println(orderID);
                        check = orderDAO.checkOrderID(orderID);
                    } while (check == true);
                    orderID = UUID.randomUUID().toString().substring(0, 5).toUpperCase();

                    String foodName;
                    int price, quantity;
                    String customerName = (String) session.getAttribute("USERNAME");
                    String phone = accountDAO.findPhone(customerName);
                    int totalMoneyOfOrder = shoppingCart.getTotal();
                    OrderDTO orderDTO = new OrderDTO(orderID, customerName, phone, checkOutDate, totalMoneyOfOrder);
                    System.out.println(orderDTO.toString());
                    if (orderDAO.insert(orderDTO)) {
                        for (FoodDTO dto : shoppingCart.getShoppingCart().values()) {
                            foodName = dto.getFoodName();
                            price = dto.getPrice();
                            quantity = dto.getQuantity();
                            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(foodName, orderID, price, quantity);
                            orderDetailDAO.insert(orderDetailDTO);
                        }
                    }
                    shoppingCart.getShoppingCart().clear();
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Your cart is empty");
                    url = ERROR;
                }
            }
        } catch (Exception e) {
            log("Error at user check out controller: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
