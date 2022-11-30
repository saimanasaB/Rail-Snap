/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.lang.*;
/**
 *
 * @author 91904
 */
@WebServlet(urlPatterns = {"/bookDao"})
public class bookDao extends HttpServlet {
    public String authenticateUser(String user, String to, String from, String depart, String returnd, int seats) throws ClassNotFoundException
     {
    	 String userName = user;
         String top=to;
         String fromp=from;
         String departt=depart;
         String returndate=returnd;
         int seatsa=seats; 
         int max=400;
         int min=100;
         int pid = (int)(Math.random()*(max-min+1)+min);  
         int amountp=0;
         int tid=0;
         String st="";
         String et="";
         try
         {
             Class.forName("com.mysql.jdbc.Driver");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","");
             Statement stmt1=con.createStatement(); 
             ResultSet rs1 = stmt1.executeQuery("select * from train");
             while(rs1.next())
             {
                String toplace = rs1.getString(4); //fetch the values present in database
                String fromplace = rs1.getString(3);
                int seatsavailable=rs1.getInt(5);
                int amount=rs1.getInt(6);
               if(top.equals(toplace) && fromp.equals(fromplace))
               {
                  tid=rs1.getInt(1);
                  st=rs1.getString(7);
                  et=rs1.getString(8);
                  if(seatsa<seatsavailable){
                      amountp=amount*seatsa;
                      break;
                  }
                  else
                  {
                      return "Not Available";
                  }
               }
             }
             PreparedStatement stmt = con.prepareStatement("insert into passenger values(?,?,?,?,?,?,?,?,?,?,?)");
             stmt.setInt(1, pid);
             stmt.setString(2, userName);
             stmt.setString(3, top); 
             stmt.setString(4, fromp);
             stmt.setString(5, departt);
             stmt.setString(6, returndate);
             stmt.setInt(7, seatsa);
             stmt.setInt(8, amountp);
             stmt.setInt(9, tid);
             stmt.setString(10,st);
             stmt.setString(11,et);
             int i= stmt.executeUpdate();
             if (i!=0)  //Just to ensure data has been inserted into the database
                 return "SUCCESS"; 
         }
             catch(SQLException e)
             {
                e.printStackTrace();
             }
         return "Oops.. Something went wrong there..!";
     }

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
