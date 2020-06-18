package com.revature.models;

public class AccountStatus {
	  private int statusId; // primary key
	  private String status; // not null, unique
	}

public enum AccountStatus {
	  Pending, Open, Closed, Denied
}