package br.com.banco.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.banco.model.Transferencia;
import br.com.banco.model.services.TransferenciaService;
import br.com.banco.repository.TransferenciaRepository;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class TransferenciaControllerTest {

	@InjectMocks
	@Autowired
	private TransferenciaController transferenciaController;

	@MockBean
	private TransferenciaService transferenciaService;

	@MockBean
	private TransferenciaRepository transferenciaRepository;

	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.transferenciaController);
	}

	@Test
	@DisplayName("Sucesso para busca de todos os dados")
	void returnSuccesGetTransferenciaAll() {
		List<Transferencia> list = new ArrayList<>();
		when(this.transferenciaRepository.findAll()).thenReturn(list);

		RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/transferencia").then()
				.statusCode(HttpStatus.SC_OK);
	}

	@Test
	@DisplayName("Sucesso para busca de todos os dados de uma data")
	void returnSuccesGetTransferenciaDate() {
		List<Transferencia> list = new ArrayList<>();
		when(this.transferenciaRepository.findAll()).thenReturn(list);
	
		RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/transferencia/busca?data_inicial=2019-01-01")
				.then().statusCode(HttpStatus.SC_OK);
	}

	@Test
	@DisplayName("Sucesso para busca de todos os dados de período")
	void returnSuccesGetTransferenciaPeriodo() {
		List<Transferencia> list = new ArrayList<>();
		when(this.transferenciaRepository.findAll()).thenReturn(list);



		RestAssuredMockMvc.given().accept(ContentType.JSON).when()
				.get("/transferencia/busca_periodo?data_inicial=2019-01-01&data_fim=2020-06-10").then()
				.statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	@DisplayName("Sucesso para busca de todos os dados por operador")
	void returnSuccesGetTransferenciaOperador() {
		List<Transferencia> list = new ArrayList<>();
		when(this.transferenciaService.filtro_nome_responsavel(list, "Beltrano")).thenReturn(list);


		RestAssuredMockMvc.given().accept(ContentType.JSON).when()
				.get("/transferencia/busca_operador?operador=Beltrano").then()
				.statusCode(HttpStatus.SC_OK);
	}
	@Test
	@DisplayName("Sucesso para busca de todos os dados por todos os filtros")
	void returnSuccesGetTransferencia() {
		List<Transferencia> list = new ArrayList<>();
		when(this.transferenciaService.filtro_nome_responsavel(list, "Beltrano")).thenReturn(list);


		RestAssuredMockMvc.given().accept(ContentType.JSON).when()
				.get("/transferencia/busca_total?data_inicial=2019-01-01&data_fim=2020-06-10&operador=Beltrano").then()
				.statusCode(HttpStatus.SC_OK);
	}

}
