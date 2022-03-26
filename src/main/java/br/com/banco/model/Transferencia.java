package br.com.banco.model;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import lombok.Data;
@Data
@Entity
public class Transferencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date data_transferencia;
	private float valor;
	private String tipo;
	private String nome_operador_transacao;
	private int conta_id;
	
	
}
