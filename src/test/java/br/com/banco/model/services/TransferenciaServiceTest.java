package br.com.banco.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@DisplayName("Teste unitário referente a classe Transferencia Service")
class TransferenciaServiceTest {

	private TransferenciaService transferenciaService = new TransferenciaService();
	private Transferencia transferencia;
	private Transferencia transferencia2;
	private Transferencia transferencia3;
	private List<Transferencia> transf;

	@MockBean
	private TransferenciaRepository transferenciaRepository;

	@BeforeEach
	public void setup() {
		transf = new ArrayList<>();

		Date data_ini = new Date(2019 - 01 - 01);
		Date data_fim = new Date(2021 - 04 - 01);
		float valor = (float) 205.25;
		Conta conta = new Conta();

		conta.setNome_responsavel("Mariana");
		transferencia = new Transferencia(5, data_fim, valor, "Deposito", "Marcos", conta);
		transferencia2 = new Transferencia(2, data_ini, valor, "Deposito", "Mariana", conta);
		transferencia3 = new Transferencia(3, data_ini, valor, "Deposito", "Paula", conta);

		transf.add(this.transferencia);
		transf.add(this.transferencia2);
		transf.add(this.transferencia3);

	}

	@Test
	@DisplayName("Retorna lista vazia, passando data que não esteja salva")
	void getListEmpty() {
		Date data = new Date();

		List<Transferencia> list = transferenciaService.filtro_data(this.transf, data);

		Assertions.assertThat(list).isEmpty();
	}

	@Test
	@DisplayName("Retorna lista de dados, passando um período")
	void getPeriodo() {
		Date data_ini = new Date(2019 - 01 - 01);
		Date data_fim = new Date(2021 - 04 - 01);

		List<Transferencia> list = transferenciaService.filtro_periodo(this.transf, data_ini, data_fim);

		Assertions.assertThat(list).isNotEmpty();
		Assertions.assertThat(list.size()).isEqualTo(3);
	}

	@Test
	@DisplayName("Retorna lista de dados referente a um operário")
	void getNomeOperario() {

		List<Transferencia> list = transferenciaService.filtro_nome_responsavel(this.transf, "Marcos");

		Assertions.assertThat(list).isNotEmpty();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}

	@Test
	@DisplayName("Retorna lista de dados de um operário referente a um período ")
	void getAllFilters() {
		Date data_ini = new Date(2019 - 01 - 01);
		Date data_fim = new Date(2021 - 04 - 01);

		List<Transferencia> list = transferenciaService.filtro_all(this.transf, data_ini, data_fim, "Marcos");

		Assertions.assertThat(list).isNotEmpty();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}

}
