package br.gov.go.sefaz.curso.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.sefaz.curso.entidades.User;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll() {
		
		User user = new User(1L, "Matheus","teteus3540@gmail.com", "(62) 999918564", "caxcaxta" );
		return ResponseEntity.ok().body(user);
	}
	
}
