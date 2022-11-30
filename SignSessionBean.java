/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proj.login;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author 91904
 */
@Stateless
public class SignSessionBean implements SignSessionBeanRemote {
   
    public String validateUser(String username, String email, String password) {
        String userName = username; 
         String pass = password; 
         String mail=email;
         String userNameDB = "";
         String passwordDB = ""; 
         try
         {
        	 Class.forName("com.mysql.jdbc.Driver");
     		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","");
             PreparedStatement stmt = con.prepareStatement("insert into login values(?,?,?)");
             stmt.setString(1, userName);
             stmt.setString(2, email);
             stmt.setString(3, password);            
             int i= stmt.executeUpdate();
             if (i!=0)  //Just to ensure data has been inserted into the database
                 return "SUCCESS"; 
         }
             
             catch(SQLException e)
             {
                e.printStackTrace();
             } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return "Oops.. Something went wrong there..!";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
