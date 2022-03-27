package br.com.banco.model;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
	@ManyToOne
	@JoinColumn(name="conta_id")
	private Conta conta_id;
	
	
	public Transferencia(int id, Date data_transferencia, float valor, String tipo, String nome_operador, Conta conta_id) {
		this.id=id;
		this.data_transferencia = data_transferencia;
		this.valor= valor;
		this.nome_operador_transacao = nome_operador;
		this.conta_id = conta_id;
		
	}
	
	public Transferencia() {}
	
}
