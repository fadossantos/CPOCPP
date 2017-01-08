package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the Inspecao database table.
 * 
 */
@Entity
@NamedQuery(name = "ProcessoCPOCPP.findAll", query = "SELECT i FROM ProcessoCPOCPP i")
public class ProcessoCPOCPP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idSecCom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtEntradaProcessoCPOCPP;

	private String observacoes;
	
	private String sinteseFatoGerador;

	@ManyToMany(cascade=  {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
	@JoinTable(
		name="processoCPOCPP_interessado"
		, joinColumns={
			@JoinColumn(name="idSECCOM")
			}
		, inverseJoinColumns={
			@JoinColumn(name="reInteressado")
			}
		)	
	private List<Interessado> interessados;
	
	@OneToMany(cascade={CascadeType.ALL})	
	@JoinColumn(name = "idSecCom")
	private List<DocumentoRelacionado> documentosRelacionados;
	
	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "idAssunto")
	private Assunto assunto;
	
	public ProcessoCPOCPP() {
		super();
	}

	

	public ProcessoCPOCPP(long idSecCom, Date dtEntradaProcessoCPOCPP, String observacoes, String sinteseFatoGerador,
			List<Interessado> interessados, List<DocumentoRelacionado> documentosRelacionados, Status status,
			Assunto assunto) {
		super();
		this.idSecCom = idSecCom;
		this.dtEntradaProcessoCPOCPP = dtEntradaProcessoCPOCPP;
		this.observacoes = observacoes;
		this.sinteseFatoGerador = sinteseFatoGerador;
		this.interessados = interessados;
		this.documentosRelacionados = documentosRelacionados;
		this.status = status;
		this.assunto = assunto;
	}



	public long getIdSecCom() {
		return idSecCom;
	}

	public void setIdSecCom(long idSecCom) {
		this.idSecCom = idSecCom;
	}

	public Date getDtEntradaProcessoCPOCPP() {
		return dtEntradaProcessoCPOCPP;
	}

	public void setDtEntradaProcessoCPOCPP(Date dtEntradaProcessoCPOCPP) {
		this.dtEntradaProcessoCPOCPP = dtEntradaProcessoCPOCPP;
	}
	
	public String getSinteseFatoGerador() {
		return sinteseFatoGerador;
	}

	public void setSinteseFatoGerador(String sinteseFatoGerador) {
		this.sinteseFatoGerador = sinteseFatoGerador;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<Interessado> getInteressados() {
		if(this.interessados == null)
		{
			this.interessados = new ArrayList<Interessado>();
		}
		return interessados;
	}

	public void setInteressados(List<Interessado> interessados) {
		this.interessados = interessados;
	}

	public List<DocumentoRelacionado> getDocumentosRelacionados() {
		if(this.documentosRelacionados == null)
		{
			this.documentosRelacionados = new ArrayList<DocumentoRelacionado>();
		}		
		return documentosRelacionados;
	}

	public void setDocumentosRelacionados(List<DocumentoRelacionado> documentosRelacionados) {
		this.documentosRelacionados = documentosRelacionados;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

}