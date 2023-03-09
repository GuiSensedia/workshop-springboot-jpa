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
//		OrderItemDomain oi1 = new OrderItemDomain(o1, product1, 2, product1.getPrice());
//		OrderItemDomain oi2 = new OrderItemDomain(o1, product3, 1, product3.getPrice());
//		OrderItemDomain oi3 = new OrderItemDomain(o2, product3, 2, product3.getPrice());
//		OrderItemDomain oi4 = new OrderItemDomain(o3, product5, 2, product5.getPrice());
