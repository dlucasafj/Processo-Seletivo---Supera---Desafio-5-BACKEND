package br.com.banco.model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.banco.model.Transferencia;

public class TransferenciaService {

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
}