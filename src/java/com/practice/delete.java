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

/**
 *
 * @author Bikash shah
 */
public class delete extends HttpServlet {

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
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ajp_project", "bikash","");
        PreparedStatement ps= con.prepareStatement("delete from user where username = ? ");
        HttpSession session = req.getSession();
        String s = (String)session.getAttribute("name");
        ps.setString(1,s);
        
        int i= ps.executeUpdate();
        
        if(i>0)
        {
            RequestDispatcher rd= req.getRequestDispatcher("signUp.jsp");
            rd.forward(req,res);
        }
     }
     
     catch(Exception e)
     {
         out.println("something weird happened" + e);
     }
 }
     

}
