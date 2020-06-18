package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.models.AccountUser;
import com.revature.util.ConnectionUtil;

public class AccountDAO implements IAccountDAO {
	@Override
	public int Insert(Account a) {

		// Step 1: Get a Connection using ConnectionUtil
		// The Connection interface represents the physical connection to the database
		try (Connection conn = ConnectionUtil.getConnection()) {

			// Step 2: Define our SQL Statements
			String columns = "balance, status_id, type_id";
			String sql = "INSERT INTO ACCOUNT (" + columns + ") VALUES (?, ?, ?)";
			// The ? marks are placeholders for input values
			// They work for PreparedStatements, and are designed to protect from SQL
			// Injection

			// Step 3: Obtain our Statement object
			// PreparedStatements are a sub-interface of Statement that provide extra
			// security to prevent
			// SQL Injection. It accomplishes this by allowing to use ? marks that we can
			// replace
			// with whatever data we want
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Step 3b: If we are using a PreparedStatement, then inject values to replace
			// the ? marks
			// We insert values into each of the ? marks above
			// Note: It is HEAVILY Recommended to use PreparedStatements instead of String
			// concatenation
			stmt.setDouble(1, a.getBalance());
			stmt.setInt(2, a.getStatus().getStatusId());
			stmt.setInt(3, a.getType().getTypeId());
			// Step 4: Execute the Statement
			return stmt.executeUpdate();

		} catch (SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means

			// This particular example might not be what you want to ultimately use
			e.printStackTrace();
		}

		return 0;
	}

	public List<Account> findAllAccount() {
		List<Account> allAccount = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT INNER JOIN ACCOUNT_STATUS ON ACCOUNT.STATUS_ID  = ACCOUNT_STATUS.ID INNER JOIN ACCOUNT_TYPE ON ACCOUNT.TYPE_ID = ACCOUNT_TYPE.ID";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				int statusId = rs.getInt("status_id");
				String status = rs.getString("status");
				int type_id = rs.getInt("type_id");
				String type = rs.getString("type");
				AccountStatus account_status = new AccountStatus(statusId, status);
				AccountType account_type = new AccountType(type_id, type);
				Account a = new Account(id, balance, account_status, account_type);
				allAccount.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return allAccount;
	}

	@Override
	public Account findAccountById(int id) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT INNER JOIN ACCOUNT_STATUS ON ACCOUNT.STATUS_ID  = ACCOUNT_STATUS.ID INNER JOIN ACCOUNT_TYPE ON ACCOUNT.TYPE_ID = ACCOUNT_TYPE.ID WHERE ACCOUNT.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				double balance = rs.getDouble("balance");
				int statusId = rs.getInt("status_id");
				String status = rs.getString("status");
				int type_id = rs.getInt("type_id");
				String type = rs.getString("type");
				AccountStatus account_status = new AccountStatus(statusId, status);
				AccountType account_type = new AccountType(type_id, type);
				return new Account(id, balance, account_status, account_type);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> findAccountByStatus(int status_id) {
		List<Account> allAccount = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT INNER JOIN ACCOUNT_STATUS ON ACCOUNT.STATUS_ID  = ACCOUNT_STATUS.ID INNER JOIN ACCOUNT_TYPE ON ACCOUNT.TYPE_ID = ACCOUNT_TYPE.ID WHERE ACCOUNT.status_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				status_id = rs.getInt("status_id");
				String status = rs.getString("status");
				int type_id = rs.getInt("type_id");
				String type = rs.getString("type");
				AccountStatus account_status = new AccountStatus(status_id, status);
				AccountType account_type = new AccountType(type_id, type);
				Account a = new Account(id, balance, account_status, account_type);
				allAccount.add(a);


			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return allAccount;
	}

	public AccountUser findAccountByUserId(int user_id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT INNER JOIN USERS_ACCOUNTS ON USERS_ACCOUNTS.ACCOUNT_ID = ACCOUNT.ID WHERE USER_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int account_id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				int statusId = rs.getInt("status_id");
				int type_id = rs.getInt("type_id");
				user_id = rs.getInt("user_id");
				int account_id2 = rs.getInt("account_id");

				return new AccountUser(account_id, balance, statusId, type_id, user_id, account_id2);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateAccount(Account a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE ACCOUNT SET ID = ?, BALANCE = ?, STATUS_ID = ?, TYPE_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a.getAccountId());
			stmt.setDouble(2, a.getBalance());
			stmt.setInt(3, a.getStatus().getStatusId());
			stmt.setInt(4, a.getType().getTypeId());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteAccount(int account_id) {
		try (Connection conn = ConnectionUtil.getConnection()) {			
			String sql = "DELETE ACCOUNT WHERE ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, account_id);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
