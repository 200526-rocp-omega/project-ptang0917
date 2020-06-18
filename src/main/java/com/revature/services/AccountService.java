package com.revature.services;

import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.IAccountDAO;
import com.revature.models.Account;
import com.revature.models.AccountUser;

public class AccountService {
	private IAccountDAO dao = new AccountDAO();
	public int Insert(Account a) {
		return dao.Insert(a);
	}
	public List<Account> findAllAccount(){
		return dao.findAllAccount();
	}
	public Account findAccountById(int id) {
		return dao.findAccountById(id);
	}
	public List<Account> findAccountByStatus(int status_id) {
		return dao.findAccountByStatus(status_id);
	}
	
	public AccountUser findAccountByUserId(int user_id) {
		return dao.findAccountByUserId(user_id);
	}

	public int updateAccount(Account a) {
		return dao.updateAccount(a);
	}
	

}
