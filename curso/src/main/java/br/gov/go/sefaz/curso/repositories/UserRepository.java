package br.gov.go.sefaz.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.go.sefaz.curso.entidades.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
