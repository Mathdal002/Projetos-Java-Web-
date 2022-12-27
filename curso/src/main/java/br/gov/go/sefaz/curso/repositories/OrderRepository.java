package br.gov.go.sefaz.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.go.sefaz.curso.entidades.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
	

}
