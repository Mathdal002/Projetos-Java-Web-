package br.gov.go.sefaz.curso.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.gov.go.sefaz.curso.entidades.User;
import br.gov.go.sefaz.curso.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfiguracao implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Pedro Oliveira", "baianos.p@gmail.com", "98839-1573", "1573");
		User u2 = new User(null, "Petrônio Veras", "petronio.veras@goias.gov.br", "(62)98174-9695", "123456");
		User u3 = new User(null, "Mathus Duarte Albuquerque", "teus4435@gmail.com", "(62)999918564", "159632");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}
	

}
