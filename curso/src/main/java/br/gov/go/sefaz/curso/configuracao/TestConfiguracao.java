package br.gov.go.sefaz.curso.configuracao;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.gov.go.sefaz.curso.entidades.Category;
import br.gov.go.sefaz.curso.entidades.Order;
import br.gov.go.sefaz.curso.entidades.OrderItem;
import br.gov.go.sefaz.curso.entidades.Payment;
import br.gov.go.sefaz.curso.entidades.Product;
import br.gov.go.sefaz.curso.entidades.User;
import br.gov.go.sefaz.curso.entidades.enums.OrderStatus;
import br.gov.go.sefaz.curso.repositories.CategoryRepository;
import br.gov.go.sefaz.curso.repositories.OrderItemRepository;
import br.gov.go.sefaz.curso.repositories.OrderRepository;
import br.gov.go.sefaz.curso.repositories.ProductRepository;
import br.gov.go.sefaz.curso.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfiguracao implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category(null, "Eletrônicos");
		Category category2 = new Category(null, "Livros");
		Category category3 = new Category(null, "Computadores");
		
		Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product product2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product product3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product product5 = new Product(null, "Rails fo Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
		
		product1.getCategories().add(category2);
		product2.getCategories().add(category1);
		product3.getCategories().add(category3);
		product4.getCategories().add(category3);
		product5.getCategories().add(category2);
		
		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
		
		User u1 = new User(null, "Pedro Oliveira", "baianos.p@gmail.com", "98839-1573", "1573");
		User u2 = new User(null, "Petrônio Veras", "petronio.veras@goias.gov.br", "(62)98174-9695", "123456");
		User u3 = new User(null, "Mathus Duarte Albuquerque", "teus4435@gmail.com", "(62)999918564", "159632");
		
		
		Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3)); 
		
		OrderItem orderItem1 = new OrderItem(order1, product1, 2, product1.getPrice());
		OrderItem orderItem2 = new OrderItem(order1, product3, 1, product3.getPrice());
		OrderItem orderItem3 = new OrderItem(order2, product3, 2, product3.getPrice());
		OrderItem orderItem4 = new OrderItem(order3, product5, 2, product5.getPrice());

		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));
		
		Payment payment1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), order1);
		
		order1.setPayment(payment1);
		
		orderRepository.save(order1);
	}
	

}
