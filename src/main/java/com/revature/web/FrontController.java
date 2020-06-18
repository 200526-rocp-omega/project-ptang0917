
package com.revature.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.authorization.AuthService;
import com.revature.controllers.AccountController;
import com.revature.controllers.UserController;
import com.revature.exceptions.AuthorizationException;
import com.revature.exceptions.NotLoggedInException;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.templates.MessageTemplate;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = -4854248294011883310L;
	private static final UserController userController = new UserController();
	private static final AccountController accountController = new AccountController();
	private static final ObjectMapper om = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("application/json");
		final String URI = req.getRequestURI().replace("/JDBCDemo", "").replaceFirst("/", "");
		
		String[] portions = URI.split("/");
		
//		System.out.println(Arrays.toString(portions));
		
		try {
			switch(portions[0]) {
			case "users":
				if(portions.length == 2) {
					// Delegate to a Controller method to handle obtaining a User by ID
					int id = Integer.parseInt(portions[1]);
					AuthService.guard(req.getSession(false), id, "Employee", "Admin");
					User u = userController.findUserById(id);
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(u));
				} else {
					// Delegate to a Controller method to handle obtaining ALL Users
					AuthService.guard(req.getSession(false), "Employee", "Admin");
					List<User> all = userController.findAllUsers();
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(all));
				}
				break;
			case "accounts":
				if(portions.length == 2) {
					int id = Integer.parseInt(portions[1]);
					AuthService.guard(req.getSession(false), id, "Employee", "Admin");
					Account a = accountController.findAccountById(id);
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(a));
				}

				else {
					AuthService.guard(req.getSession(false), "Employee", "Admin");
					List<Account> all = accountController.findAllAccount();
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(all));
				}
				if(portions.length > 2) {
					if(portions[1] == "status") {
						int status_id = Integer.parseInt(portions[2]);
						AuthService.guard(req.getSession(false), "Employee", "Admin");
						List<Account> accountList = accountController.findAccountByStatus(status_id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(accountList));
					}
				}
				else {
					AuthService.guard(req.getSession(false), "Employee", "Admin");
					List<Account> all = accountController.findAllAccount();
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(all));
				}
				break;
			}
		} catch(AuthorizationException e) {
			res.setStatus(401);
			MessageTemplate message = new MessageTemplate("The incoming token has expired");
			
			res.getWriter().println(om.writeValueAsString(message));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("application/json");
		final String URI = req.getRequestURI().replace("/JDBCDemo", "").replaceFirst("/", "");
		
		String[] portions = URI.split("/");

		try {
			switch(portions[0]) {
			case "logout":
				if(userController.logout(req.getSession(false))) {
					res.setStatus(200);
					res.getWriter().println("You have been successfully logged out");
				} else {
					res.setStatus(400);
					res.getWriter().println("You were not logged in to begin with");
				}
				break;
			}
		} catch(NotLoggedInException e) {
			res.setStatus(401);
			MessageTemplate message = new MessageTemplate("The incoming token has expired");
			res.getWriter().println(om.writeValueAsString(message));
		}
	}
	
//	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		res.setContentType("application/json");
//		final String URI = req.getRequestURI().replace("/JDBCDemo", "").replaceFirst("/", "");
//		String[] portions = URI.split("/");
//		try {
//			switch(portions[0]) {
//			case "users":
//				if(portions.length == 2) {
//					// Delegate to a Controller method to handle obtaining a User by ID
//					int id = Integer.parseInt(portions[1]);
//					AuthService.guard(req.getSession(false), "Admin");
//					HttpSession  session = req.getSession();
//					BufferedReader reader = req.getReader();
//					StringBuilder sb = new StringBuilder();
//					String line; 
//					
//					res.setStatus(200);
//					res.getWriter().println(om.writeValueAsString(u));
//				} else {
//					// Delegate to a Controller method to handle obtaining ALL Users
////					List<User> all = userController.findAllUsers(req.getSession(false));
//					AuthService.guard(req.getSession(false), "Admin");
//					List<User> all = userController.findAllUsers();
//					res.setStatus(200);
//					res.getWriter().println(om.writeValueAsString(all));
//				}
//				break;
//				
//			}
//		}
//		catch(NotLoggedInException e){
//			res.setStatus(401);
//			MessageTemplate message = new MessageTemplate("The incoming token has expired");
//			res.getWriter().println(om.writeValueAsString(message));
//		}
//		
//	}

}