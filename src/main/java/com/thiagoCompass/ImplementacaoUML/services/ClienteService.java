package com.thiagoCompass.ImplementacaoUML.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagoCompass.ImplementacaoUML.domain.Cliente;
import com.thiagoCompass.ImplementacaoUML.repositories.ClienteRepository;
import com.thiagoCompass.ImplementacaoUML.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
}
