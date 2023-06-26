package com.itested.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itested.user.User;

import ContextDb.ConnexionDB;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	private Connection conn;
	private String uemail;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		
		String uemail =request.getParameter("username");
		String upwd =request.getParameter("password");
		HttpSession session = request.getSession();
		
		user.setUemail(uemail);
		user.setUpwd(upwd);

		
		RequestDispatcher dispatcher = null;
		
		if(user.getUemail() == null) {
			request.setAttribute("status", "invalidEmail");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
		if(user.getUpwd() == null) {
			request.setAttribute("status", "invalidUpwd");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
		conn = ConnexionDB.getConnexion();
		String query = "select * from users where uemail = ? and upwd = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user.getUemail());
			pst.setString(2, user.getUpwd());
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				session.setAttribute("email", rs.getString("uemail"));
				session.setAttribute("name", rs.getString("uname"));
				dispatcher = request.getRequestDispatcher("index.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
