package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class UserController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String REGISTER = "RegisterController";
    private static final String USER_SEARCH_FOOD = "UserSearchFoodController";
    private static final String USER_ADD_CART = "UserAddCartController";
    private static final String USER_DELETE_CART_FOOD = "UserDeleteCartController";
    private static final String USER_UPDATE_CART_FOOD = "UserUpdateCartController";
    private static final String CHECK_OUT = "UserCheckOutController";
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
            String  action = request.getParameter("action");
            if(action.equals("Register Account")){
                url = REGISTER;
            }else if(action.equals("User Search Food")){
                url = USER_SEARCH_FOOD;
            }else if(action.equals("Add")){
                url = USER_ADD_CART;
                System.out.println("toi add cua user");
            } else if(action.equals("Delete")){
                url = USER_DELETE_CART_FOOD;
            } else if(action.equals("Update")){
                url = USER_UPDATE_CART_FOOD;
            } else if(action.equals("Check Out")){
                url = CHECK_OUT;
            }
        } catch (Exception e) {
            log("Error at User controller: " + e.getMessage());
        }finally{
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
