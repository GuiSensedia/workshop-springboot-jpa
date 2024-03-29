package com.educandoweb.course.model.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.educandoweb.course.model.dto.request.CreateCategoryRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_category")
public class CategoryDomain implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public static CategoryDomain valueOf (CreateCategoryRequest request){
		CategoryDomain domain = CategoryDomain.builder()
				.name(request.getName())
				.build();
		return domain;
	}

}