package com.thiagoCompass.ImplementacaoUML;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thiagoCompass.ImplementacaoUML.domain.Categoria;
import com.thiagoCompass.ImplementacaoUML.repositories.CategoriaRepository;

@SpringBootApplication
public class ImplementacaoUmlApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriarepository;

	public static void main(String[] args) {
		SpringApplication.run(ImplementacaoUmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriarepository.saveAll(Arrays.asList(cat1,cat2));
		
	}

}
