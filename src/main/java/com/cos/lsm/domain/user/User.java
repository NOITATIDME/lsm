package com.cos.lsm.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 20, unique = true) // nullable = false =>null 사용여부, length = 20 => 길이 20byte , unique = true => unique 제약걸기
	private String username;
	@Column(nullable = false, length = 20)
	private String password;
	@Column(nullable = false, length = 50)
	private String email;

}
