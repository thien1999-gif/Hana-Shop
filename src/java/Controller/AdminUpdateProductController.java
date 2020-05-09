/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FoodDAO;
import DTO.FoodDTO;
import DTO.FoodDTOErrorObj;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class AdminUpdateProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "AdminSearchProductController";
    private static final String INVALID = "adminUpdateProduct.jsp";
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
            String foodName= request.getParameter("txtFoodName");
            String imageLink = request.getParameter("txtImageLink");
            String description = request.getParameter("txtDescription");
            String priceStr = request.getParameter("txtPrice");
            int price = 0;
            String categoryFood = request.getParameter("cbCategory");
            String status = request.getParameter("cbStatus");
            
            FoodDTOErrorObj errorObj = new FoodDTOErrorObj();
            boolean valid = true;
            if (!imageLink.contains(".jpg") && !imageLink.contains(".png")) {
                valid = false;
                errorObj.setImageLinkError("Image only support *.png or *.jpg");
            }
            if (description.equals("")) {
                valid = false;
                errorObj.setDescriptionError("Description can't be blank");
            }
            try {
                  price = Integer.parseInt(priceStr);
                  if(price <= 0){
                    valid = false;
                    errorObj.setPriceError("Price must be  > 0");
                }
            } catch (Exception e) {
                valid = false;
                errorObj.setPriceError("Price must is a number");
            }
            if(categoryFood.equals("") || categoryFood.equals("-1")){
                valid = false;
                errorObj.setCategoryError("Please choose category food");
            }
            if(status.equals("") || status.equals("-1")){
                valid = false;
                errorObj.setStatusError("Please choose status food");
            }
            if(valid){
                FoodDAO dao = new FoodDAO();
                FoodDTO dto = new FoodDTO(foodName,imageLink, description, Boolean.parseBoolean(status), Integer.parseInt(categoryFood), price);
                if(dao.update(dto)){
                    url = SUCCESS;
                }
                else{
                    request.setAttribute("ERROR", "Update failed");
                }
            }else{
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("Error at admin update product controller: " + e.getMessage());
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
