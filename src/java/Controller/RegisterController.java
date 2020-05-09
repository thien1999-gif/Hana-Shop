/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDAO;
import DTO.AccountDTO;
import DTO.AccountDTOErrorObj;
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
public class RegisterController extends HttpServlet {

    private static final String SUCCESS = "index.jsp";
    private static final String INVALID = "register.jsp";
    private static final String ERROR = "error.jsp";
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
            String fullName = request.getParameter("txtFullname");
            String email = request.getParameter("txtEmail");
            String phone = request.getParameter("txtPhone");
            String address = request.getParameter("txtAddress");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            
            boolean valid = true;
            AccountDTOErrorObj errorObj = new AccountDTOErrorObj();
            
            if(userName.equals("")){
                valid = false;
                errorObj.setUserNameError("User name can't be blank");
            }
            if(fullName.equals("")){
                valid = false;
                errorObj.setFullNameError("Full Name can't be blank");
            }
            if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                valid = false;
                errorObj.setEmailError("Email can't be blank");
            }
            if(!(phone.matches("\\d{10,12}"))){
                valid = false;
                errorObj.setPhoneError("Phone must be number");
            }
            if(address.equals("")){
                valid = false;
                errorObj.setAddressError("Address can't be blank");
            }
            if(!password.matches("\\w{6,30}")){
                valid = false;
                errorObj.setPasswordError("Password must have from 6 to 30 character");
            }
            if(!confirm.equals(password)){
                valid = false;
                errorObj.setConfirmError("Confirm password doesn't like password");
            }
               
            if(valid){
                AccountDAO dao = new AccountDAO();
                AccountDTO dto = new AccountDTO(userName, password, fullName, email, phone, address);
                if(dao.insert(dto)){
                    url = SUCCESS;
                }else{
                    request.setAttribute("ERROR", "Register failed");
                }
            }else{
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("Error at register controller: " + e.getMessage());
            if(e.getMessage().contains("duplicate")){
                AccountDTOErrorObj errorObj = new AccountDTOErrorObj();
                errorObj.setUserNameError("User name is exited");
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }
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
