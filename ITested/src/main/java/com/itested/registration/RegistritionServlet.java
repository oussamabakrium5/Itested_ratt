package com.itested.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.itested.user.User;

import ContextDb.ConnexionDB;

@WebServlet("/register")
public class RegistritionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
	private Connection conn;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		
		String uname =request.getParameter("name");
		String uemail =request.getParameter("email");
		String upwd =request.getParameter("pass");
		String reupwd =request.getParameter("re_pass");
		String umobile =request.getParameter("contact");
		
		user.setUname(uname);
		user.setUemail(uemail);
		user.setUpwd(upwd);
		user.setReupwd(reupwd);
		user.setUmobil(umobile);
		
		RequestDispatcher dispatcher = null;
		
		if(user.getUname() == null) {
			request.setAttribute("status", "invalidName");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		if(user.getUemail() == null) {
			request.setAttribute("status", "invalidEmail");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		if(user.getUpwd() == null) {
			request.setAttribute("status", "invalidUpwd");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		if(user.getReupwd() == null) {
			request.setAttribute("status", "invalidReUpwd");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		} else if (!user.getUpwd().equals(user.getReupwd())){
			request.setAttribute("status", "invalidConfirmPassword");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		if(user.getUmobil() == null) {
			request.setAttribute("status", "invalidMobile");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		
		conn = ConnexionDB.getConnexion();
		String query = "insert into users(uname,upwd,uemail,umobile) values(?,?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user.getUname());
			pst.setString(2, user.getUpwd());
			pst.setString(3, user.getUemail());
			pst.setString(4, user.getUmobil());
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if(rowCount > 0) {
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("status", "failed");
			}
			
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
