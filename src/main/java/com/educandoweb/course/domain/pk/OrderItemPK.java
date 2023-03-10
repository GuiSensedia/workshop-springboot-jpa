package com.educandoweb.course.domain.pk;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.domain.OrderDomain;
import com.educandoweb.course.domain.ProductDomain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class OrderItemPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = " order_id")
	private OrderDomain order;

	@ManyToOne
	@JoinColumn(name = " product_id")
	private ProductDomain product;

}

