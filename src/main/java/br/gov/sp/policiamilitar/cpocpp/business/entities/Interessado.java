package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Interessado.findAll", query="SELECT a FROM Interessado a")
public class Interessado implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private long reInteressado;

	private String nomeInteressado;

	@ManyToMany(mappedBy = "interessados", cascade=  {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
	private List<ProcessoCPOCPP> processosCPOCPP;

	public Interessado() {
		super();
	}

	public Interessado(long reInteressado, String nomeInteressado, List<ProcessoCPOCPP> documentos) {
		super();
		this.reInteressado = reInteressado;
		this.nomeInteressado = nomeInteressado;
		this.processosCPOCPP = documentos;
	}

	public long getReInteressado() {
		return reInteressado;
	}

	public void setReInteressado(long reInteressado) {
		this.reInteressado = reInteressado;
	}

	public String getNomeInteressado() {
		return nomeInteressado;
	}

	public void setNomeInteressado(String nomeInteressado) {
		this.nomeInteressado = nomeInteressado;
	}

	public List<ProcessoCPOCPP> getProcessosCPOCPP() {
		return processosCPOCPP;
	}

	public void setProcessosCPOCPP(List<ProcessoCPOCPP> processosCPOCPP) {
		this.processosCPOCPP = processosCPOCPP;
	}
}
