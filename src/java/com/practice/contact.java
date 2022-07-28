package com.practice;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class contact extends HttpServlet {

@Override
public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Your message</h2>");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String message=request.getParameter("message");
        
        out.println("<h2> Name : "+ name +"</h2>");
        out.println("<h2> Email : "+ email +"</h2>");
        out.println("<h2> Message : "+ message +"</h2>");
        
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ajp_project", "bikash","");
        PreparedStatement ps= con.prepareStatement("insert into contact values(?,?,?)");
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, message);
               
        int i = ps.executeUpdate();

        if(i>0)
        {
            RequestDispatcher rd= request.getRequestDispatcher("contact.html");
            rd.forward(request,response);
        }
        con.close();
        
    } catch (Exception e) {
        out.println(""+e);
    }
        
    }
    
}
