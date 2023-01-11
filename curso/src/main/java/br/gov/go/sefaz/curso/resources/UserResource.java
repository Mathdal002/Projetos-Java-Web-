package br.gov.go.sefaz.curso.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.go.sefaz.curso.Service.UserService;
import br.gov.go.sefaz.curso.entidades.User;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User object = service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User object) {
		object = service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				 .buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).body(object);
	}
	
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity <User> Update(@PathVariable Long id, @RequestBody User object) {
		object = service.update(id, object);
		return ResponseEntity.ok().body(object);
	}
}
