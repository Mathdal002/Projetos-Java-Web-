package br.gov.go.sefaz.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.go.sefaz.curso.entidades.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	

}
