package br.gov.go.sefaz.curso.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.go.sefaz.curso.Service.Exceptions.DatabaseException;
import br.gov.go.sefaz.curso.Service.Exceptions.ResourceNotFoundException;
import br.gov.go.sefaz.curso.entidades.User;
import br.gov.go.sefaz.curso.repositories.UserRepository;;

@Service
public class UserService {
	
	@Autowired
	public UserRepository repository;
	
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
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public User update(Long id, User object) {
		try {
				User entity = repository.getOne(id);
				updateData(entity, object);
				return repository.saveAndFlush(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
			
		}
	}
	
	public void updateData(User entity, User object) {
		entity.setNome(object.getNome());
		entity.setEmail(object.getEmail());
		entity.setPhone(object.getPhone());
	}

}
