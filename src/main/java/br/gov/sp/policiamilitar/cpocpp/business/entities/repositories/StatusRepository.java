package br.gov.sp.policiamilitar.cpocpp.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.policiamilitar.cpocpp.business.entities.Status;


@Repository
@Transactional
public interface StatusRepository extends CrudRepository<Status, Long> {
	
	public Status findByDescStatus(String descStatus);
    
}
