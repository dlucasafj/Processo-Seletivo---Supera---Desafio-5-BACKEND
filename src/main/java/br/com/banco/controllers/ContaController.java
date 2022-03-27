package br.com.banco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;

	@GetMapping()
	public List<Conta> listar_transferencia() {

		return contaRepository.findAll();
	}
}
