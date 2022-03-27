package br.com.banco.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Testes para Tranferencia Repositorio")
class TransferenciaRepositoryTest {

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Test
	@DisplayName("Cria um novo dado de transferencia")
	public void createdTransferencia() {
		Date data = new Date();

		Conta conta = new Conta();

		conta.setNome_responsavel("Mariana");

		float valor = (float) 205.25;

		Transferencia transferencia = new Transferencia(5, data, valor, "Deposito", "Marcos", conta);

		this.transferenciaRepository.save(transferencia);

		Assertions.assertThat(transferencia).isNotNull();

		Assertions.assertThat(transferencia.getId()).isEqualTo(5);

		Assertions.assertThat(transferencia.getNome_operador_transacao()).isEqualTo("Marcos");

		Assertions.assertThat(transferencia.getValor()).isEqualTo(valor);

	}

	@Test
	@DisplayName("Busca uma transferencia específica")
	public void getTransferencia() {

		Assertions.assertThat(this.transferenciaRepository.findById(5)).isNotNull();

	}

}
