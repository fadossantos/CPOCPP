package br.gov.sp.policiamilitar.cpocpp.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.policiamilitar.cpocpp.business.entities.ProcessoCPOCPP;


@Repository
@Transactional
public interface ProcessoCPOCPPRepository extends CrudRepository<ProcessoCPOCPP, Long> {
    
}
