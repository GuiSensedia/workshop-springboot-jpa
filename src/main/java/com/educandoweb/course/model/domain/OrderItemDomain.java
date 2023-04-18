package com.educandoweb.course.model.domain;

import java.io.Serializable;

import com.educandoweb.course.model.domain.pk.OrderItemPK;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_order_item")
public class OrderItemDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Integer quantity;

	private Double price;

	public Double getSubTotal() {
		return price * quantity;
	}

}
