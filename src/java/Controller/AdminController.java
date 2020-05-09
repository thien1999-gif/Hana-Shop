/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AdminController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADD_PRODUCT = "AdminAddProductController";
    private static final String SEARCH_PRODUCT = "AdminSearchProductController";
    private static final String DELETE_PRODUCT = "AdminDeleteProductController";
    private static final String EDIT_PRODUCT = "AdminEditProductController";
    private static final String UPDATE_PRODUCT = "AdminUpdateProductController";
    private static final String SEARCH_USER = "AdminSearchUserController";
    private static final String DELETE_USER = "AdminDeleteUserController";
    private static final String VIEW_TRANSACTION = "AdminViewTransactionController";
    private static  final String VIEW_ALL_TRANSACTION = "AdminViewAllTransactionController";
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
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("ROLE");
        String action = request.getParameter("action");
        try {
            if (action.equals("Add product") && role.equals("admin")) {
                url = ADD_PRODUCT;
            } else if (action.equals("Admin Search Food") && role.equals("admin")) {
                url = SEARCH_PRODUCT;
            } else if (action.equals("Delete") && role.equals("admin")) {
                url = DELETE_PRODUCT;
            } else if(action.equals("Edit") && role.equals("admin")){
                url = EDIT_PRODUCT;
            }else if(action.equals("Update product") && role.equals("admin")){
                url = UPDATE_PRODUCT;
            }else if(action.equals("Admin Search User") && role.equals("admin")){
                url = SEARCH_USER;
            } else if(action.equals("Delete User") && role.equals("admin")){
                url = DELETE_USER;
            } else if(action.equals("View") && role.equals("admin")){
                url = VIEW_TRANSACTION;
            }else if(action.equals("View all") && role.equals("admin")){
                url = VIEW_ALL_TRANSACTION;
            }
            
            else {
                request.setAttribute("ERROR", "Your action is invalid");
            }
        } catch (Exception e) {
            log("Error at Admin controller: " + e.getMessage());
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
