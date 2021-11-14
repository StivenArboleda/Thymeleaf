package com.icesi.edu.Stiven.model.person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class UserModel {
	@Id
	private long id;
	@NotBlank
	private String userName;
	private String password;
	private UserType type;

}
