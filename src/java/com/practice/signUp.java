package com.practice;

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

public class signUp extends HttpServlet {

@Override
public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>You have login to this webpage</h2>");
        String name=request.getParameter("username");
        String password1=request.getParameter("password");
        String password2=request.getParameter("confpassword");
        String cond=request.getParameter("condition");
        if(cond.equals("seller"))
        {
            out.println("<h2> Name : "+ name +"</h2>");
            out.println("<h2> Password : "+ password1 +"</h2>");
            out.println("<h2> Conform Password : "+ password2 +"</h2>");
            out.println("<h2> You are a seller</h2>");
        }
        else
        {
            out.println("<h2> Name : "+ name +"</h2>");
            out.println("<h2> Password : "+ password1 +"</h2>");
            out.println("<h2> Conform Password : "+ password2 +"</h2>");
            out.println("<h2> You are a buyer</h2>");
        }
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ajp_project", "bikash","");
        PreparedStatement ps= con.prepareStatement("insert into user values(?,?)");
        ps.setString(1, name);

        
        if(password1.equals(password2)){
          
            
            ps.setString(2, password1);   
        }
        
        else
        {
            HttpSession session = request.getSession();
            session.setAttribute("error", "1");
            RequestDispatcher rd= request.getRequestDispatcher("signUp.jsp");
            rd.forward(request,response);
        }
        
        
        int i = ps.executeUpdate();

        if(i>0)
        {
            RequestDispatcher rd= request.getRequestDispatcher("login.jsp");
            rd.forward(request,response);
        }
        con.close();
        
    } catch (Exception e) {
        out.println(""+e);
    }
        
        
        
    }
    
}

