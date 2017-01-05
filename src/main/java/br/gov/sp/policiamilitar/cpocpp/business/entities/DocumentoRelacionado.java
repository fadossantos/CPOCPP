package br.gov.sp.policiamilitar.cpocpp.business.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="DocumentoRelacionado.findAll", query="SELECT a FROM DocumentoRelacionado a")
public class DocumentoRelacionado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idDocumentoRelacionado;
	
	private String protocoloPM;
	
	private String descDocumentoRelacionado;
	
	private String nomeDocumentoRelacionado;
	
	private byte[] arrayBytes;
	
	@ManyToOne
	@JoinColumn(name = "idTipoDocumento")
	private TipoDocumento tipoDocumento;
	
	@ManyToOne
	private ProcessoCPOCPP processoCPOCPP;



}
