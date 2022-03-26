package br.com.banco.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Transferencia;
import br.com.banco.model.services.DateService;
import br.com.banco.model.services.TransferenciaService;
import br.com.banco.repository.TransferenciaRepository;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	/**
	 * Método que faz a busca de todos os dados na Base de Dados
	 * 
	 * @return Todas as transferências
	 */
	@GetMapping()
	public List<Transferencia> listar_transferencia() {

		return transferenciaRepository.findAll();
	}

	/**
	 * Este método retorna uma lista de transferências com base nas datas informadas
	 * 
	 * @param data_ini
	 * @param data_fim
	 * @return Lista Transferência
	 */
	@GetMapping("/busca")
	public List<Transferencia> get_date_filter(
			@RequestParam(name = "data_inicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_ini,
			@RequestParam(name = "data_fim", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_fim) {

		List<Transferencia> transferencias = transferenciaRepository.findAll();
		TransferenciaService trans_service = new TransferenciaService();

		trans_service.filtro_data(transferencias, data_ini);
		return trans_service.filtro_data(transferencias, data_ini);
	}

}
