package br.gov.go.sefaz.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.go.sefaz.curso.entidades.OrderItem;
import br.gov.go.sefaz.curso.entidades.pk.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {

}