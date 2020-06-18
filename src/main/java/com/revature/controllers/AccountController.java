package com.revature.controllers;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountUser;
import com.revature.services.AccountService;

public class AccountController {
	private final AccountService accountService = new AccountService();
	public List<Account> findAllAccount(){
		return accountService.findAllAccount();
	}
	
	public Account findAccountById(int id) {
		return accountService.findAccountById(id);
	}
	
	public List<Account> findAccountByStatus(int status_id) {
		return accountService.findAccountByStatus(status_id);
	}
	public AccountUser findAccountByUserId(int user_id) {
		return accountService.findAccountByUserId(user_id);
	}
	
}
