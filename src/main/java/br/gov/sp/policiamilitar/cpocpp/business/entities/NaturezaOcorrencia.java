package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="NaturezaOcorrencia.findAll", query="SELECT s FROM NaturezaOcorrencia s")
public class NaturezaOcorrencia implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idNaturezaOcorrencia;
	
	private String descNaturezaOcorrencia;
	
	
	public NaturezaOcorrencia(){
		
	}
	
	public NaturezaOcorrencia(long idNaturezaOcorrencia, String descNaturezaOcorrencia) {
		super();
		this.idNaturezaOcorrencia = idNaturezaOcorrencia;
		this.descNaturezaOcorrencia = descNaturezaOcorrencia;
	}

	public long getIdNaturezaOcorrencia() {
		return idNaturezaOcorrencia;
	}

	public void setIdNaturezaOcorrencia(long idNaturezaOcorrencia) {
		this.idNaturezaOcorrencia = idNaturezaOcorrencia;
	}

	public String getDescNaturezaOcorrencia() {
		return descNaturezaOcorrencia;
	}

	public void setDescNaturezaOcorrencia(String descNaturezaOcorrencia) {
		this.descNaturezaOcorrencia = descNaturezaOcorrencia;
	}

	

	
}
