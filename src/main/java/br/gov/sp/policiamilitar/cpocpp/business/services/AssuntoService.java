package br.gov.sp.policiamilitar.cpocpp.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.policiamilitar.cpocpp.business.entities.Assunto;
import br.gov.sp.policiamilitar.cpocpp.business.entities.repositories.AssuntoRepository;

@Service
public class AssuntoService {
    
    @Autowired
    private AssuntoRepository assuntoRepository; 
    
    
    public AssuntoService() {
        super();
    }  
        
    public Iterable<Assunto> findAll() {
        return this.assuntoRepository.findAll();
    }
    
    public Assunto findOne(Long id) {
        return this.assuntoRepository.findOne(id);
    }    

    public Assunto findByName(String name){
    	return this.assuntoRepository.findByDescAssunto(name);
    }
    
    public void addOrUpdate(final Assunto assunto) {
        this.assuntoRepository.save(assunto);
    }
    
    public void remove(final Long id){
    	this.assuntoRepository.delete(id);
    }
    
}
