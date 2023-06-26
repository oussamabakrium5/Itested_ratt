package com.itested.user;

public class User {

	
	private String uname;
	private String uemail;
	private String upwd;
	private String reupwd;
	private String umobil;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		if (uname == null || uname.equals("")) {
			this.uname = null;
		}
		this.uname = uname;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		if (uemail == null || uemail.equals("")) {
			this.uemail = null;
		}
		
		int lenght = uemail.length();
		int valide = 0;
		char lettre;
		
		for (int i = 0; i < lenght; i++) {
			lettre = uemail.charAt(i);
			if (lettre == '@') {
				valide = 1;
				break;
			}
		}
		
		if(valide == 1) {
			this.uemail = uemail;
		} else {
			this.uemail = null;
		}
		
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		if (upwd == null || upwd.equals("")) {
			this.upwd = null;
		}
		this.upwd = upwd;
	}
	public String getReupwd() {
		return reupwd;
	}
	public void setReupwd(String reupwd) {
		if (reupwd == null || reupwd.equals("")) {
			this.reupwd = null;
		}
		this.reupwd = reupwd;
	}
	public String getUmobil() {
		return umobil;
	}
	public void setUmobil(String umobil) {
		if (umobil == null || umobil.equals("")) {
			this.umobil = null;
		}
		this.umobil = umobil;
	}
	
	
}
