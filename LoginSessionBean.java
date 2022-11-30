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
public class LoginSessionBean implements LoginSessionBeanRemote {
    @Override
    public String validateUser(String username, String password) {
        String userName = username; 
         String pass = password;     	 
         String userNameDB = "";
         String passwordDB = ""; 
         try
         {
        	 Class.forName("com.mysql.jdbc.Driver");
     		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","");
     		 Statement stmt=con.createStatement(); 
             ResultSet rs = stmt.executeQuery("select * from login"); 
             while(rs.next()) // Until next row is present otherwise it return false
             {
              userNameDB = rs.getString(1); //fetch the values present in database
              passwordDB = rs.getString(3);
 
               if(userName.equals(userNameDB) && pass.equals(passwordDB))
               {
                  return "SUCCESS"; ////If the user entered values are already present in the database, which means user has already registered so return a SUCCESS message.
               }
             }
         }
             catch(SQLException e)
             {
                e.printStackTrace();
             } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "Invalid User -- Sign Up";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
