package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import javax.persistence.*;
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

	@ManyToMany(mappedBy = "processosCPOCPP")
	private List<Interessado> interessados;
	
	@ManyToMany(mappedBy = "processosCPOCPP")
	private List<ProcessoRelacionado> processosRelacionados;
	
	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "idAssunto")
	private Assunto assunto;
	
	public ProcessoCPOCPP() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ProcessoCPOCPP(long idSecCom, Date dtEntradaProcessoCPOCPP, String observacoes, String sinteseFatoGerador,
			List<Interessado> interessados, List<ProcessoRelacionado> processosRelacionados, Status status,
			Assunto assunto) {
		super();
		this.idSecCom = idSecCom;
		this.dtEntradaProcessoCPOCPP = dtEntradaProcessoCPOCPP;
		this.observacoes = observacoes;
		this.sinteseFatoGerador = sinteseFatoGerador;
		this.interessados = interessados;
		this.processosRelacionados = processosRelacionados;
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
		return interessados;
	}

	public void setInteressados(List<Interessado> interessados) {
		this.interessados = interessados;
	}

	public List<ProcessoRelacionado> getProcessosRelacionados() {
		return processosRelacionados;
	}

	public void setProcessosRelacionados(List<ProcessoRelacionado> processosRelacionados) {
		this.processosRelacionados = processosRelacionados;
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