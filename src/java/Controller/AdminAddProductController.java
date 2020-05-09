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
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class AdminAddProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminMain.jsp";
    private static final String INVALID = "adminAddProduct.jsp";

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
            String foodName = request.getParameter("txtFoodName");
            String imageLink = request.getParameter("txtImageLink");
            String description = request.getParameter("txtDescription");
            String priceStr = request.getParameter("txtPrice");
            int price = 0;
            String createDate = request.getParameter("txtCreateDate");
            String categoryFood = request.getParameter("cbCategory");
            FoodDTOErrorObj errorObj = new FoodDTOErrorObj();

            boolean valid = true;
            if (foodName.equals("")) {
                valid = false;
                errorObj.setFoodNameError("Food name can't be blank");
            }
            
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

            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            Date today = java.util.Calendar.getInstance().getTime();

            try {
                Date date = df.parse(createDate);
                if (date.compareTo(today) < 0) {
                    valid = false;
                    errorObj.setCreateDateError("Check In Date is invalid!");
                }
            } catch (Exception e) {
                valid = false;
                errorObj.setCreateDateError("Check In Date is invalid!");
            }
            
            if(categoryFood.equals("") || categoryFood.equals("-1")){
                valid = false;
                errorObj.setCategoryError("Please choose category food");
            }
            
            if (valid) {
                FoodDAO dao = new FoodDAO();
                FoodDTO dto= new FoodDTO(foodName, imageLink, description, createDate, price, Integer.parseInt(categoryFood));
                if(dao.insert(dto)){
                    url = SUCCESS;
                }
                else{
                    request.setAttribute("ERROR", "Insert failed");
                }
            }else{
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }
        } catch (Exception e) {
            log("Error at Admin product controller: " + e.getMessage());
            
            if(e.getMessage().contains("duplicate")){
                FoodDTOErrorObj errorObj = new FoodDTOErrorObj();
                errorObj.setFoodNameError("Food name is exited");
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }
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
        /*
        try {
            CategoryFoodDAO dao = new CategoryFoodDAO();
            List<CategoryFoodDTO> listCategory = dao.loadFoodCategory();
            request.setAttribute("LIST_CATEGORY_FOOD", listCategory);
        } catch (Exception e) {
            log("Error at load data: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("adminAddProduct.jsp").forward(request, response);
        }*/
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
