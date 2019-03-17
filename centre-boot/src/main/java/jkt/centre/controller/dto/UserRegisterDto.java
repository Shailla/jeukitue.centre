package jkt.centre.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class UserRegisterDto {
	
	@Getter	@Setter
	private String login;
	
	@Getter	@Setter
	private String password;
	
	@Getter	@Setter	@Email	@NotNull
	private String mail;
}
