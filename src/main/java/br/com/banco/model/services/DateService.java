package br.com.banco.model.services;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateService {

	/**
	 * Método que compara as datas vinda do objeto no banco de dados com a data
	 * passada como parâmetro
	 * 
	 * @param data_bd      -> Data do objeto do banco de Dados
	 * @param data_inicial -> Data passada na URL de pesquisa
	 * @return boolean
	 */
	public boolean comparaData(Date data_bd, Date data_inicial) {

		ZonedDateTime data_ini = data_inicial.toInstant().atZone(ZoneId.systemDefault());
		ZonedDateTime dbTime = data_bd.toInstant().atZone(ZoneId.systemDefault());

		return dbTime.toLocalDate().equals(data_ini.toLocalDate());
	}

}
