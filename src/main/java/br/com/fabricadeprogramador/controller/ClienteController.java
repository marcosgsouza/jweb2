package br.com.fabricadeprogramador.controller;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fabricadeprogramador.model.Cliente;
import br.com.fabricadeprogramador.repository.ClienteRepository;

@Named
@ViewScoped
public class ClienteController {
	
	
	private Cliente cliente;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void salvar(){
		clienteRepository.save(cliente);
	}

	
	
}
