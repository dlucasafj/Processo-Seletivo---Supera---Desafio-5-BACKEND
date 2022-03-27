package br.com.banco.model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banco.model.Transferencia;

/**
 * Classe de Serviço responsável por manipular os dados de Transferencias
 * @author daniel
 *
 */
@Service
public class TransferenciaService {

	/**
	 * Método  responsável por buscar os dados de acordo com a data especificada
	 * @param transferencias -> lista de todas as transferencias
	 * @param data_ini -> data especificada para busca
	 * @return List<Transferencia>
	 */
	public List<Transferencia> filtro_data(List<Transferencia> transferencias, Date data_ini){
		List<Transferencia> busca = new ArrayList<Transferencia>();
		DateService date_service = new DateService();
		
		
		transferencias.forEach(transferencia -> {
			Date data_banco = transferencia.getData_transferencia();
			boolean response = date_service.comparaData(data_banco, data_ini);
			if (response) {
				busca.add(transferencia);
			}
		});
		
		return busca;
	}
	
	
	/**
	 * Método responsável por buscar os dados de acordo com um período especificado
	 * @param transferencias -> lista de todas as transferências
	 * @param data_ini -> Data inicial do período
	 * @param data_fim -> Data final do período
	 * @return List<Transferencia>
	 */
	public List<Transferencia> filtro_periodo(List<Transferencia>transferencias, Date data_ini,Date data_fim){
		List<Transferencia> busca = new ArrayList<Transferencia>();
		DateService date_service = new DateService();
		transferencias.forEach(transferencia -> {
			Date data_banco = transferencia.getData_transferencia();
			boolean response = date_service.periodo(data_banco, data_ini,data_fim);
			if (response) {
				busca.add(transferencia);
			}
		});
		
		return busca;
	}
	
	/**
	 * Método responsável por buscar as transações feita por um operador
	 * @param transferencias -> Lista de todas as transferencias
	 * @param responsavel -> Nome do responsável pela operação
	 * @return List<Transferencia> -> Lista de todas as transações feita pelo operador informado
	 */
	public List<Transferencia> filtro_nome_responsavel(List<Transferencia> transferencias, String responsavel){
		List<Transferencia> busca = new ArrayList<Transferencia>();
		
		transferencias.forEach(transferencia->{
			String operador = transferencia.getNome_operador_transacao();
			if(operador !=null) {
				if(operador.equals(responsavel)) {
					busca.add(transferencia);
				}
			};
			
		});
		return busca;
	}
	
	/**
	 * Método responsável por buscar as transações de acordo com um período e um nome de operador
	 * @param transferencias -> Array com todas as tranferencias cadastradas
	 * @param data_ini -> Data inicial do período
	 * @param data_fim -> Data final do período
	 * @param responsavel -> Nome do operador responsável
	 * @return List<Transferencia> -> Lista de todas as transações feitas no período e pelo responsável informados
	 */
	public List<Transferencia> filtro_all(List<Transferencia> transferencias, Date data_ini,Date data_fim, String responsavel){
		List<Transferencia> busca = new ArrayList<Transferencia>();
		DateService date_service = new DateService();
		
		transferencias.forEach(transferencia->{
			
			Date data_banco = transferencia.getData_transferencia();
			boolean response_periodo= date_service.periodo(data_banco, data_ini, data_fim);
			String operador = transferencia.getNome_operador_transacao();
			
			if(operador!=null) {
				if(response_periodo && operador.equals(responsavel)) {
					busca.add(transferencia);
				}
			}
						
		});
		return busca;
	}
	
	
}
