package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Assunto.findAll", query="SELECT s FROM Assunto s")
public class Assunto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idAssunto;
	
	private String descAssunto;

	public long getIdAssunto() {
		return idAssunto;
	}

	public void setIdAssunto(long idAssunto) {
		this.idAssunto = idAssunto;
	}

	public String getDescAssunto() {
		return descAssunto;
	}

	public void setDescAssunto(String descAssunto) {
		this.descAssunto = descAssunto;
	}

	public Assunto(long idAssunto, String descAssunto) {
		super();
		this.idAssunto = idAssunto;
		this.descAssunto = descAssunto;
	}

	public Assunto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
}
