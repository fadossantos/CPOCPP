package br.gov.sp.policiamilitar.cpocpp.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.policiamilitar.cpocpp.business.entities.Status;
import br.gov.sp.policiamilitar.cpocpp.business.entities.repositories.StatusRepository;

@Service
public class StatusService {
    
    @Autowired
    private StatusRepository statusRepository; 
    
    
    public StatusService() {
        super();
    }  
        
    public Iterable<Status> findAll() {
        return this.statusRepository.findAll();
    }
    
    public Status findOne(Long id) {
        return this.statusRepository.findOne(id);
    }    

    public Status findByName(String name){
    	return this.statusRepository.findByDescStatus(name);
    }
    
    public void addOrUpdate(final Status status) {
        this.statusRepository.save(status);
    }
    
    public void remove(final Long id){
    	this.statusRepository.delete(id);
    }
    
}
