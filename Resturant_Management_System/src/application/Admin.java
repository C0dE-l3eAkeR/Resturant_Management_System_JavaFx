
package application;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import DataBase.DBConnect;

public class Admin implements Serializable {

	String UserName;
	String Password;

	public Admin() {
	}

	public Admin(String UserName, String Password) {
		this.UserName = UserName;
		try {
			this.Password = hashing(Password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String hashing(String pass) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(pass.getBytes());
		String str = new String(messageDigest.digest());
		return str;
	}

	public String getUserName() {
		return UserName;
	}

	public String getPassword() {
		return Password;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	static Admin getAdmin() {
		Admin admin = new Admin();

		return admin;

	}

	public void setPassword(String Password) throws NoSuchAlgorithmException {

		this.Password = hashing(Password);

	}

	@Override
	public String toString() {
		return null;
	}

}
