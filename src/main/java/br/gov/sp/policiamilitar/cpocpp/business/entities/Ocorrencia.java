package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Ocorrencia.findAll", query = "SELECT s FROM Ocorrencia s")
public class Ocorrencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idOcorrencia;

	private Date dataOcorrencia;

	private TipoLocal tipoLocal;

	private Endereco endereco;

	private NaturezaOcorrencia naturezaOcorrencia;

	public Ocorrencia() {

	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public TipoLocal getTipoLocal() {
		return tipoLocal;
	}

	public void setTipoLocal(TipoLocal tipoLocal) {
		this.tipoLocal = tipoLocal;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public NaturezaOcorrencia getNaturezaOcorrencia() {
		return naturezaOcorrencia;
	}

	public void setNaturezaOcorrencia(NaturezaOcorrencia naturezaOcorrencia) {
		this.naturezaOcorrencia = naturezaOcorrencia;
	}

	public long getIdOcorrencia() {
		return idOcorrencia;
	}

	public void setIdOcorrencia(long idOcorrencia) {
		this.idOcorrencia = idOcorrencia;
	}

}
