package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.templates.LoginTemplate;


public class LoginServlet extends HttpServlet {
	
	private ObjectMapper om = new ObjectMapper();
	private UserService service = new UserService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession  session = req.getSession();
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line; 
		
		User current = (User) session.getAttribute("currentUser");
		
		// Already logged in
		if(current != null) {
			res.setStatus(400);
			res.getWriter().println("You are already logged in as user " + current.getUsername());
			return;
		}
		
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		/*
		 * The (line = reader.readLine()) part obtains the value for a single line
		 * from the body of the request and stores it into the line variable
		 * 
		 * Then the != null part compares the value of the string to null
		 * If the readLine() method is null, that means we are at the end
		 */
		String body = sb.toString();
		LoginTemplate lt = om.readValue(body, LoginTemplate.class);
		//System.out.println(lt);
		PrintWriter writer = res.getWriter();
		User u = service.login(lt);
		if(u == null) {
			//Login Failed
			res.setStatus(400);
			
			writer.println("Username or password was incorrect");
			return;
		}
		
		
		session.setAttribute("currentUser", u);
		
		res.setStatus(200);
		writer.println(om.writeValueAsString(u));
		res.setContentType("application/json");

	}

}