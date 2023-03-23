package com.educandoweb.course.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.educandoweb.course.model.dto.request.CreateUserRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_user")
public class UserDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String email;

	private String phone;

	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private final List<OrderDomain> orders = new ArrayList<>();

	public static UserDomain valueOf(CreateUserRequest request){
		UserDomain domain = UserDomain.builder()
				.name(request.getName())
				.email(request.getEmail())
				.phone(request.getPhone())
				.password(request.getPassword())
				.build();
		return domain;
	}

}
