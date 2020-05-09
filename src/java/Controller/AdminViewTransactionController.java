/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.OrderDAO;
import DTO.OrderDTO;
import DTO.OrderDTOErrorObj;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AdminViewTransactionController extends HttpServlet {

    private static final String INVALID = "adminViewHistory.jsp";
    private static final String SUCCESS = "adminViewHistory.jsp";
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
            String dateFrom = request.getParameter("txtDateFrom");
            String dateTo = request.getParameter("txtDateTo");
            boolean valid = true;
            OrderDTOErrorObj errorObj = new OrderDTOErrorObj();
            List<OrderDTO> resultList;
            HttpSession session = request.getSession();
            //check date input null
            if (dateFrom.equals("")) {
                valid = false;
                errorObj.setDateFromErrorObj("Please choose date from !");
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
                return;
            }
            if (dateTo.equals("")) {
                valid = false;
                errorObj.setDateToErrorObj("Please choose date to !");
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
                return;
            }
            //check date to < date from  3D PORN COMIC: IM TIFALUN HOT BUSTY GIRL
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            fm.setLenient(false);
            Date strDateFrom = fm.parse(dateFrom);
            Date strDateTo = fm.parse(dateTo);

            if (strDateTo.compareTo(strDateFrom) < 0) {
                valid = false;
                errorObj.setDateToErrorObj("Date to is invalid!");
            }
            
            if (valid) {
                OrderDAO dao = new OrderDAO();
                resultList = dao.listViewHistory(dateFrom, dateTo);
                session.setAttribute("LIST_VIEW_HISTORY", resultList);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at admin view transaction controller: " + e.getMessage());
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
