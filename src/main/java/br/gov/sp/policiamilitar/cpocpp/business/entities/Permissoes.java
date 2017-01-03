package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Permissoes.findAll", query="SELECT s FROM Permissoes s")
public class Permissoes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idPermissao;
	
	private long cpf;
	
	private String permissao;

	public long getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(long idPermissao) {
		this.idPermissao = idPermissao;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public Permissoes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permissoes(long idPermissao, long cpf, String permissao) {
		super();
		this.idPermissao = idPermissao;
		this.cpf = cpf;
		this.permissao = permissao;
	}

	
}
