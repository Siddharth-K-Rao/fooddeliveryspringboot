package com.learning.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "register")
public class Register implements Comparable<Register>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Size(max = 55)
	@NotBlank
	private String name;
	
	@Size(max = 50)
	@Email
	private String email;
	
	@Size(max = 150)
	@NotBlank
	private String password;
	
	@NotBlank
	private String address;
	
	
	@OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
	private Login login;
	
	
	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
