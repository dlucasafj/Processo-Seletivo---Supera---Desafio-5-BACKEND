package br.com.banco.model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.banco.model.Transferencia;

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
			System.out.println(response);
			if (response) {
				busca.add(transferencia);
			}
		});
		
		return busca;
	}
}
