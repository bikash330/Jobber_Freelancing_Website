/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Bikash shah
 */
public class updatepass extends HttpServlet {

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
 {
     PrintWriter out = res.getWriter();
     
     try
     {
         String password1 = req.getParameter("password");
         String password2 = req.getParameter("newpassword");
         String password3 = req.getParameter("confpassword");
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ajp_project", "bikash","");
        PreparedStatement ps= con.prepareStatement("select * from user where username = ?  ");
        HttpSession session = req.getSession();
        String s = (String)session.getAttribute("name");
        ps.setString(1,s);
        
        ResultSet i= ps.executeQuery();
        
        if(i.next())
        {
            PreparedStatement ps1= con.prepareStatement("update user set password = ? where username = ?  ");
            if(password2.equals(password3))
            {
                 
                ps1.setString(1,password2);
                 ps1.setString(2,s); 
                 ps1.executeUpdate();
                 RequestDispatcher rd= req.getRequestDispatcher("dashboard.jsp");
                rd.include(req,res);
            }
            else
            {
                session.setAttribute("error", "2");
                RequestDispatcher rd= req.getRequestDispatcher("updatepass.jsp");
                rd.include(req,res);
            }
            
        }
        else
        {
         
            session.setAttribute("error", "1");
            RequestDispatcher rd= req.getRequestDispatcher("updatepass.jsp");
            rd.include(req,res);
        }
     }
     
     catch(Exception e)
     {
         out.println("something weird happened" + e);
     }
 }
     

}
