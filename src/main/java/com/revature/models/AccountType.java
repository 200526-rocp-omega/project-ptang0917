package com.revature.models;

public class AccountType {
	 private int typeId; // primary key
	 private String type; // not null, unique
}

public enum AccountType {
	 Checking, Savings
}