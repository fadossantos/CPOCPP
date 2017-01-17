package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name="Historico.findAll", query="SELECT s FROM Historico s")
public class Historico implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idHistorico;
	
	private String descHistorico;
	
	@OneToOne(cascade={CascadeType.ALL})	
	@JoinColumn(name = "idDocumentoRelacionado")
	private DocumentoRelacionado documentoRelacionado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataGravacao;	
	
	public Date getDataGravacao() {
		return dataGravacao;
	}

	public void setDataGravacao(Date dataGravacao) {
		this.dataGravacao = dataGravacao;
	}

	public DocumentoRelacionado getDocumentoRelacionado() {
		return documentoRelacionado;
	}

	public void setDocumentoRelacionado(DocumentoRelacionado documentoRelacionado) {
		this.documentoRelacionado = documentoRelacionado;
	}

	public Historico(){
		
	}
	
	public Historico(long idHistorico, String descHistorico) {
		super();
		this.idHistorico = idHistorico;
		this.descHistorico = descHistorico;
	}

	public long getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(long idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String getDescHistorico() {
		return descHistorico;
	}

	public void setDescHistorico(String descHistorico) {
		this.descHistorico = descHistorico;
	}

	

	
}
