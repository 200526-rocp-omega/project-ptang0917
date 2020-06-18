package com.revature.models;

public class Role {
	  private int roleId; // primary key
	  private String role; // not null, unique
}

public enum Role {
	  Standard, Premium, Employee, Admin
}
