package br.gov.go.sefaz.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.go.sefaz.curso.entidades.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	

}
