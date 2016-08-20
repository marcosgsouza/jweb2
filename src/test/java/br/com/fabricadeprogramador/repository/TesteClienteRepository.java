package br.com.fabricadeprogramador.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fabricadeprogramador.model.Cliente;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TesteClienteRepository {
	
	@Autowired
	ClienteRepository repository;
	
	@Test
	public void deveSalvar(){
		Cliente cliente = new Cliente();
		cliente.setNome("Jão");
		cliente.setEmail("jao@hotmail.com");
		cliente.setCpf("999999999");
		
		Cliente salvo = repository.save(cliente);
		
//		if(salvo.getId() != null){
//		System.out.println("Acertou");
//		}else
//			System.out.println("Errou");
		
		Assert.assertNotNull(salvo.getId());
	}
	
	@Test
	public void deveBuscarTodos(){
		
		Cliente jao = new Cliente("Jao da Silva", "jao@gmail.com", "9999999999");
		Cliente ze = new Cliente("Ze da Silva", "ze@gmail.com", "88888888");
		Cliente pafuncio = new Cliente("Pafuncio da Silva", "pafuncio@gmail.com", "7777777777");
		
		repository.save(jao);
		repository.save(ze);
		repository.save(pafuncio);
		
		List clientes = repository.findAll();
		Assert.assertEquals(3, clientes.size());
		
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void naoDeveSalvarSemNome(){
		
		Cliente jao = new Cliente();
		jao.setEmail("jao@gmail.com");
		jao.setCpf("99999");
		
		repository.save(jao);
				
		
	}
	
	@Test()
	public void naoDeveSalvarComCPFMaiorQueOnzeDigito(){
		
		Cliente jao = new Cliente();
		jao.setNome("Joa da Silva ");
		jao.setEmail("jao@gmail.com");
		jao.setCpf("999999999999999999999999999999999999999999");
		
		repository.save(jao);
	}
}
