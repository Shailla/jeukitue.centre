package jkt.centre.controller.form;

import javax.validation.constraints.NotNull;

public class UserUpdatePasswordForm {
	
	@NotNull
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
