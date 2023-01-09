package br.gov.go.sefaz.curso.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.sefaz.curso.entidades.User;
import br.gov.go.sefaz.curso.repositories.UserRepository;
import br.gov.go.sefaz.curso.Service.Exceptions.ResourceNotFoundException;;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> object = repository.findById(id);
		return object.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User object) {
		 return repository.save(object);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public User update(Long id, User object) {
		User entity = repository.getOne(id);
		updateData(entity, object);
		return repository.saveAndFlush(entity);
	}
	
	private void updateData(User entity, User object) {
		entity.setNome(object.getNome());
		entity.setEmail(object.getEmail());
		entity.setPhone(object.getPhone());
	}

}
