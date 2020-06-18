package com.revature.controllers;

import java.util.List;

import com.revature.models.Account;
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
	
	
}
