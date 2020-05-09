/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDAO;
import DTO.AccountDTOErrorObj;
import DTO.CartDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    private static final String ADMIN = "adminMain.jsp";
    private static final String USER = "index.jsp";
    private static final String INVALID = "login.jsp";
    private static final String ERROR = "login.jsp";
    private static final String USER_VIEW_CART = "userViewCart.jsp";
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
            String userName = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            HttpSession session = request.getSession();
            //check data dau vao
            boolean valid = true;
            AccountDTOErrorObj errorObj = new AccountDTOErrorObj();
            if (userName.equals("")) {
                valid = false;
                errorObj.setUserNameError("User Name can't be blank");
            }
            if (password.equals("")) {
                valid = false;
                errorObj.setPasswordError("Password can't be blank");
            }
            //check login
            if (valid) {
                AccountDAO dao = new AccountDAO();
                String role = dao.checkLogin(userName, password);
                session.setAttribute("ROLE", role);
                session.setAttribute("USERNAME", userName);
                if (role.equals("failed")) {
                    request.setAttribute("ERROR", "Invalid username or password");
                    url = ERROR;
                } else {
                    if (role.equals("admin")) {
                        url = ADMIN;
                    } else if(role.equals("user")){
                        String con = (String) session.getAttribute("CONTINUE");
                        System.out.println(con);
                       if ( con != null) {
                            CartDTO shoppingCart = (CartDTO) session.getAttribute("CART");
                            shoppingCart.setCustomerName(userName);
                            url = USER_VIEW_CART;
                        } else {
                            url = USER;
                        }
                    }
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("Error at login controller: " + e.getMessage());
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
