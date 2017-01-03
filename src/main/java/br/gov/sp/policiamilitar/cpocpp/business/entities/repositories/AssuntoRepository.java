package br.gov.sp.policiamilitar.cpocpp.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.policiamilitar.cpocpp.business.entities.Assunto;


@Repository
@Transactional
public interface AssuntoRepository extends CrudRepository<Assunto, Long> {
	
	public Assunto findByDescAssunto(String descAssunto);
    
}
