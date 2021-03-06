package br.gov.sp.policiamilitar.cpocpp.business.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@NamedQuery(name="DocumentoRelacionado.findAll", query="SELECT a FROM DocumentoRelacionado a")
public class DocumentoRelacionado {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idDocumentoRelacionado;
	
	private String protocoloPM;
	
	private String descDocumentoRelacionado;
	
	private String nomeDocumentoRelacionado;	
	
	private String caminhoArquivoSalvo;
	
	@Transient
	private MultipartFile file;	
	
	public String getCaminhoArquivoSalvo() {
		return caminhoArquivoSalvo;
	}

	public void setCaminhoArquivoSalvo(String caminhoArquivoSalvo) {
		this.caminhoArquivoSalvo = caminhoArquivoSalvo;
	}

	public MultipartFile getFile() {
		return file;
	}
	
	public void setFile(MultipartFile file) {
		this.file = file;		
	}

	@ManyToOne
	@JoinColumn(name = "idTipoDocumento")
	private TipoDocumento tipoDocumento;
	
	@ManyToOne
	private ProcessoCPOCPP processoCPOCPP;	

	public DocumentoRelacionado(long idDocumentoRelacionado, String protocoloPM, String descDocumentoRelacionado,
			String nomeDocumentoRelacionado, TipoDocumento tipoDocumento,
			ProcessoCPOCPP processoCPOCPP) {
		super();
		this.idDocumentoRelacionado = idDocumentoRelacionado;
		this.protocoloPM = protocoloPM;
		this.descDocumentoRelacionado = descDocumentoRelacionado;
		this.nomeDocumentoRelacionado = nomeDocumentoRelacionado;
		this.tipoDocumento = tipoDocumento;
		this.processoCPOCPP = processoCPOCPP;
	}
	
	
	public DocumentoRelacionado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdDocumentoRelacionado() {
		return idDocumentoRelacionado;
	}

	public void setIdDocumentoRelacionado(long idDocumentoRelacionado) {
		this.idDocumentoRelacionado = idDocumentoRelacionado;
	}

	public String getProtocoloPM() {
		return protocoloPM;
	}

	public void setProtocoloPM(String protocoloPM) {
		this.protocoloPM = protocoloPM;
	}

	public String getDescDocumentoRelacionado() {
		return descDocumentoRelacionado;
	}

	public void setDescDocumentoRelacionado(String descDocumentoRelacionado) {
		this.descDocumentoRelacionado = descDocumentoRelacionado;
	}

	public String getNomeDocumentoRelacionado() {
		return nomeDocumentoRelacionado;
	}

	public void setNomeDocumentoRelacionado(String nomeDocumentoRelacionado) {		
		this.nomeDocumentoRelacionado = nomeDocumentoRelacionado;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public ProcessoCPOCPP getProcessoCPOCPP() {
		return processoCPOCPP;
	}

	public void setProcessoCPOCPP(ProcessoCPOCPP processoCPOCPP) {
		this.processoCPOCPP = processoCPOCPP;
	}

}
