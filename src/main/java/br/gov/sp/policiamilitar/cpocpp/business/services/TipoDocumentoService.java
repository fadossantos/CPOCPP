package br.gov.sp.policiamilitar.cpocpp.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.policiamilitar.cpocpp.business.entities.TipoDocumento;
import br.gov.sp.policiamilitar.cpocpp.business.entities.repositories.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {
    
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository; 
    
    
    public TipoDocumentoService() {
        super();
    }  
        
    public Iterable<TipoDocumento> findAll() {
        return this.tipoDocumentoRepository.findAll();
    }
    
    public TipoDocumento findOne(Long id) {
        return this.tipoDocumentoRepository.findOne(id);
    }    

    public TipoDocumento findByName(String name){
    	return this.tipoDocumentoRepository.findByDescTipoDocumento(name);
    }
    
    public void addOrUpdate(final TipoDocumento tipoDocumento) {
        this.tipoDocumentoRepository.save(tipoDocumento);
    }
    
    public void remove(final Long id){
    	this.tipoDocumentoRepository.delete(id);
    }
    
}
