package com.thiagoCompass.ImplementacaoUML.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagoCompass.ImplementacaoUML.domain.Pedido;
import com.thiagoCompass.ImplementacaoUML.repositories.PedidoRepository;
import com.thiagoCompass.ImplementacaoUML.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
}
