package br.com.banco.model.services;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Classe responsável por toda a manipulação que envolve datas dentro da
 * aplicação
 * 
 * @author daniel
 *
 */
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

	/**
	 * Método compara as datas e verifica se existe uma data dentro de um período especificado
	 * @param data_bd -> Data do objeto do banco de Dados
	 * @param data_inicial -> Data inicial do período requerido
	 * @param data_final -> Data final do período requerido
	 * @return boolean
	 */
	public boolean periodo(Date data_bd, Date data_inicial, Date data_final) {

		// se data_bd for igual a data_inicial ou data_final-> Salva

		if (this.comparaData(data_bd, data_inicial) || this.comparaData(data_bd, data_final)) {
			return true;
		} else if (data_bd.after(data_inicial) && data_bd.before(data_final)) { // se data_bd vier depois de
																				// data_inicial e antes de data_final ->
																				// Salva
			return true;
		} else {
			return false;
		}

	}

}
