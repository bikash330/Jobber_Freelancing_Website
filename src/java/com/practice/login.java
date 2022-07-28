package com.practice;

import com.mysql.cj.protocol.Resultset;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login extends HttpServlet {

@Override
public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>You have login to this webpage</h2>");
        String name=request.getParameter("username");
        String password=request.getParameter("password");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ajp_project", "bikash","");
        PreparedStatement ps= con.prepareStatement("select * from user where username= ? and password = ?");
        ps.setString(1, name);
        ps.setString(2, password);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            HttpSession session = request.getSession();
            session.setAttribute("name" , name);
            RequestDispatcher rd= request.getRequestDispatcher("dashboard.jsp");
            rd.forward(request,response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("error", "1");
            RequestDispatcher rd= request.getRequestDispatcher("login.jsp");
            rd.forward(request,response);
            }
        }catch(Exception ex)
        {
            out.println(ex);
        }    
    }
    
}
