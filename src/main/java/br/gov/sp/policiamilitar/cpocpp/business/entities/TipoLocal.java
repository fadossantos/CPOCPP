package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="TipoLocal.findAll", query="SELECT s FROM TipoLocal s")
public class TipoLocal implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idTipoLocal;
	
	private String descTipoLocal;
	
	
	public TipoLocal(){
		
	}
	
	public TipoLocal(long idTipoLocal, String descTipoLocal) {
		super();
		this.idTipoLocal = idTipoLocal;
		this.descTipoLocal = descTipoLocal;
	}

	public long getIdTipoLocal() {
		return idTipoLocal;
	}

	public void setIdTipoLocal(long idTipoLocal) {
		this.idTipoLocal = idTipoLocal;
	}

	public String getDescTipoLocal() {
		return descTipoLocal;
	}

	public void setDescTipoLocal(String descTipoLocal) {
		this.descTipoLocal = descTipoLocal;
	}

	

	
}
