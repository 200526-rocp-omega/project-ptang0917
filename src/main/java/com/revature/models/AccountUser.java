package com.revature.models;

import java.util.Objects;

public class AccountUser {
	private int accountId; // primary key
	private double balance; // not null
	private int status_id;
	private int type_id;
	private int user_id;
	private int accountId2;
	public AccountUser(int accountId, double balance, int status_id, int type_id, int user_id, int accountId2) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.status_id = status_id;
		this.type_id = type_id;
		this.user_id = user_id;
		this.accountId2 = accountId2;
	}
	public AccountUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getAccountId2() {
		return accountId2;
	}
	public void setAccountId2(int accountId2) {
		this.accountId2 = accountId2;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountId, accountId2, balance, status_id, type_id, user_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AccountUser)) {
			return false;
		}
		AccountUser other = (AccountUser) obj;
		return accountId == other.accountId && accountId2 == other.accountId2
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& status_id == other.status_id && type_id == other.type_id && user_id == other.user_id;
	}
	@Override
	public String toString() {
		return "AccountUser [accountId=" + accountId + ", balance=" + balance + ", status_id=" + status_id
				+ ", type_id=" + type_id + ", user_id=" + user_id + ", accountId2=" + accountId2 + "]";
	}
	

}
