package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="SituacaoOcorrencia.findAll", query="SELECT s FROM SituacaoOcorrencia s")
public class SituacaoOcorrencia implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idSituacaoOcorrencia;
	
	private String descSituacaoOcorrencia;

	public long getIdSituacaoOcorrencia() {
		return idSituacaoOcorrencia;
	}

	public void setIdSituacaoOcorrencia(long idSituacaoOcorrencia) {
		this.idSituacaoOcorrencia = idSituacaoOcorrencia;
	}

	public String getDescSituacaoOcorrencia() {
		return descSituacaoOcorrencia;
	}

	public void setDescSituacaoOcorrencia(String descSituacaoOcorrencia) {
		this.descSituacaoOcorrencia = descSituacaoOcorrencia;
	}

	public SituacaoOcorrencia(long idSituacaoOcorrencia, String descSituacaoOcorrencia) {
		super();
		this.idSituacaoOcorrencia = idSituacaoOcorrencia;
		this.descSituacaoOcorrencia = descSituacaoOcorrencia;
	}

	public SituacaoOcorrencia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
}
