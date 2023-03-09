package com.educandoweb.course.domain;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.domain.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "tb_order_item")
public class OrderItemDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	private Integer quantity;
	private Double price;

	public OrderItemDomain(OrderDomain order, ProductDomain product, Integer quantity, Double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	@JsonIgnore
	public OrderDomain getOrder() {
		return id.getOrder();
	}
	public void setOrder(OrderDomain order) {
		id.setOrder(order);
	}
	public ProductDomain getProduct() {
		return id.getProduct();
	}
	public void setProduct(ProductDomain product) {
		id.setProduct(product);
	}
	public Double getSubTotal() {
		return price * quantity;
	}

}
