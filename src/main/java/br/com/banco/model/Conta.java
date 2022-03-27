package br.com.banco.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_conta;
	private String nome_responsavel;
}
