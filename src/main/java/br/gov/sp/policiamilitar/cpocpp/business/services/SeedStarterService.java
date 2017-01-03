
package br.gov.sp.policiamilitar.cpocpp.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.policiamilitar.cpocpp.business.entities.SeedStarter;
import br.gov.sp.policiamilitar.cpocpp.business.entities.repositories.SeedStarterRepository;

@Service
public class SeedStarterService {
    
    @Autowired
    private SeedStarterRepository seedstarterRepository; 
    
    
    public SeedStarterService() {
        super();
    }     
    
    public Iterable<SeedStarter> findAll() {
        return this.seedstarterRepository.findAll();
    }

    public void add(final SeedStarter seedStarter) {
        this.seedstarterRepository.save(seedStarter);
    }
    
}
