package br.com.banco.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Transferencia;
import br.com.banco.model.services.TransferenciaService;
import br.com.banco.repository.TransferenciaRepository;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaRepository transferenciaRepository;
	private TransferenciaService trans_service = new TransferenciaService();

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
//		TransferenciaService trans_service = new TransferenciaService();

//		trans_service.filtro_data(transferencias, data_ini);
		return this.trans_service.filtro_data(transferencias, data_ini);
	}

	/**
	 * Método responsável por fazer uma busca dos dados de acordo com o período
	 * informado
	 * 
	 * @param data_ini -> Data inicial
	 * @param data_fim -> Data final
	 * @return List<Transferencia> -> Lista de Transferência referente ao período
	 */
	@GetMapping("/busca_periodo")
	public List<Transferencia> get_data_por_periodo(
			@RequestParam(name = "data_inicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_ini,
			@RequestParam(name = "data_fim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_fim) {

		List<Transferencia> transferencias = transferenciaRepository.findAll();
//		TransferenciaService trans_service = new TransferenciaService();

		return this.trans_service.filtro_periodo(transferencias, data_ini, data_fim);

	}

	/**
	 * Método responsável por fazer a busca das transferências de acordo com o nome
	 * do operador responsável
	 * 
	 * @param name_responsavel -> nome do operador responsável
	 * @return List<Transferencia> ->Lista de transferencia feita pelo operador
	 */
	@GetMapping("/busca_operador")
	public List<Transferencia> get_transfer_name_responsavel(@RequestParam(name = "operador") String name_responsavel) {
		List<Transferencia> transferencias = transferenciaRepository.findAll();
//		TransferenciaService trans_service = new TransferenciaService();

		return this.trans_service.filtro_nome_responsavel(transferencias, name_responsavel);
	}

	/**
	 * Método responsável por fazer a busca das transferências de acordo com um período e o nome do operador responsável
	 * @param data_ini Date<Date> -> data inicial do período
	 * @param data_fim Date<Date> -> data final do período
	 * @param name_responsavel String<String> -> nome do operador responsável
	 * @return List<Transferencia> ->Lista de transferencia feita pelo operador
	 */
	@GetMapping("/busca_total")
	public List<Transferencia> get_all_filters(
			@RequestParam(name = "data_inicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_ini,
			@RequestParam(name = "data_fim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_fim,
			@RequestParam(name = "operador") String name_responsavel) {
		List<Transferencia> transferencias = transferenciaRepository.findAll();
		
		
		return this.trans_service.filtro_all(transferencias, data_ini, data_fim, name_responsavel);
	}
}
