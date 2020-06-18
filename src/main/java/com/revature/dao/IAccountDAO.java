package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface IAccountDAO {
	public int Insert(Account a);
	public List<Account> findAllAccount();
	public Account findAccountById(int id);
	public List<Account> findAccountByStatus(int status_id);
	public Account findAccountByUserId(int user_id);
	public int updateAccount(Account a);
	public int deleteAccount(int account_id);
	
	
}
