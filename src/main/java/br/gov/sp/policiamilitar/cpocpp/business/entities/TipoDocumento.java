package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="TipoDocumento.findAll", query="SELECT s FROM TipoDocumento s")
public class TipoDocumento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idTipoDocumento;
	
	private String descTipoDocumento;
	
	
	public TipoDocumento(){
		
	}
	
	public TipoDocumento(long idTipoDocumento, String descTipoDocumento) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.descTipoDocumento = descTipoDocumento;
	}

	public long getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getDescTipoDocumento() {
		return descTipoDocumento;
	}

	public void setDescTipoDocumento(String descTipoDocumento) {
		this.descTipoDocumento = descTipoDocumento;
	}

	

	
}
