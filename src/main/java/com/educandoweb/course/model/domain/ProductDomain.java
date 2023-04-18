package com.educandoweb.course.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.educandoweb.course.model.dto.request.CreateProductRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tb_product")
public class ProductDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Double price;

	private String imgUrl;

	private CategoryDomain category;
	
	@OneToMany(mappedBy = "id.product")
	private final List<OrderItemDomain> items = new ArrayList<>();

	public static  ProductDomain valueOf(CreateProductRequest request, CategoryDomain category){
		ProductDomain domain = ProductDomain.builder()
				.name(request.getName())
				.description(request.getDescription())
				.price(request.getPrice())
				.category(category)
				.build();
		return domain;
	}

}
