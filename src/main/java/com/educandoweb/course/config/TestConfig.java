package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import com.educandoweb.course.model.domain.pk.OrderItemPK;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.model.domain.OrderDomain;
import com.educandoweb.course.model.domain.OrderItemDomain;
import com.educandoweb.course.model.domain.PaymentDomain;
import com.educandoweb.course.model.domain.ProductDomain;
import com.educandoweb.course.model.domain.UserDomain;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.repositories.UserRepository;

@RequiredArgsConstructor
@Configuration
@Profile("loc")
public class TestConfig implements CommandLineRunner {

	private final UserRepository userRepository;

	private final OrderRepository orderRepository;

	private final CategoryRepository categoryRepository;

	private final ProductRepository productRepository;

	private final OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) {

		CategoryDomain category1 = CategoryDomain.builder()
				.name("Eletronics")
				.build();

		CategoryDomain category2 = CategoryDomain.builder()
				.name("Books")
				.build();

		CategoryDomain category3 = CategoryDomain.builder()
				.name("Computers")
				.build();
		categoryRepository.saveAll(Arrays.asList(category1,category2,category3));

		ProductDomain product1 = ProductDomain.builder()
				.name("The Lord Of The Rings")
				.description("Lorem ipsum dolor sit amet, consectetur.")
				.price(90.5)
				.build();

		ProductDomain product2 = ProductDomain.builder()
				.name("Smart TV")
				.description("Nulla eu imperdiet purus. Maecenas ante")
				.price(2190.0)
				.build();

		ProductDomain product3 = ProductDomain.builder()
				.name("Macbook Pro")
				.description("Nam eleifend maximus tortor, at mollis.")
				.price(1250.0)
				.build();

		ProductDomain product4 = ProductDomain.builder()
				.name("PC Gamer")
				.description("Donec aliquet odio ac rhoncus cursus.")
				.price(1200.0)
				.build();

		ProductDomain product5 = ProductDomain.builder()
				.name("Rails for Dummies")
				.description("Cras fringilla convallis sem vel faucibus.")
				.price(100.99)
				.build();
		productRepository.saveAll(Arrays.asList(product1,product2,product3,product4,product5));

		product1.getCategories().add(category2);
		product2.getCategories().add(category1);
		product2.getCategories().add(category3);
		product3.getCategories().add(category3);
		product4.getCategories().add(category3);
		product5.getCategories().add(category2);
		productRepository.saveAll(Arrays.asList(product1,product2,product3,product4,product5));

		UserDomain user1 = UserDomain.builder()
				.name("Maria Brown")
				.email("maria@gmail.com")
				.phone("988888888")
				.password("123456")
				.build();

		UserDomain user2 = UserDomain.builder()
				.name("Alex Green")
				.email("alex@gmail.com")
				.phone("977777777")
				.password("654321")
				.build();
		userRepository.saveAll(Arrays.asList(user1,user2));

		OrderDomain order1 = OrderDomain.builder()
				.moment(Instant.parse("2019-06-20T19:53:07Z"))
				.orderStatus(2)
				.client(user1)
				.build();

		OrderDomain order2= OrderDomain.builder()
				.moment(Instant.parse("2019-07-21T03:42:10Z"))
				.orderStatus(1)
				.client(user2)
				.build();

		OrderDomain order3 = OrderDomain.builder()
				.moment(Instant.parse("2019-07-22T15:21:22Z"))
				.orderStatus(1)
				.client(user1)
				.build();
		orderRepository.saveAll(Arrays.asList(order1,order2,order3));

		OrderItemDomain orderItem1 = OrderItemDomain.builder()
				.id(new OrderItemPK(order1, product1))
				.quantity(2)
				.price(product1.getPrice())
				.build();

		OrderItemDomain orderItem2 = OrderItemDomain.builder()
				.id(new OrderItemPK(order1, product3))
				.quantity(1)
				.price(product3.getPrice())
				.build();

		OrderItemDomain orderItem3 = OrderItemDomain.builder()
				.id(new OrderItemPK(order2, product3))
				.quantity(2)
				.price(product3.getPrice())
				.build();

		OrderItemDomain orderItem4 = OrderItemDomain.builder()
				.id(new OrderItemPK(order3, product5))
				.quantity(2)
				.price(product5.getPrice())
				.build();
		orderItemRepository.saveAll(Arrays.asList(orderItem1,orderItem2,orderItem3,orderItem4));

		PaymentDomain payment = PaymentDomain.builder()
				.moment(Instant.parse("2019-06-20T21:53:07Z"))
				.order(order1)
				.build();

		order1.setPayment(payment);

		orderRepository.save(order1);

	}
}
