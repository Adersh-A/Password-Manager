package com.myprojects.passwordmanager.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

	@NotBlank
	private String name;
	@Email
	@NotNull
	private String email;
	@NotBlank
	@Min(5)
	private String password;
	
}
