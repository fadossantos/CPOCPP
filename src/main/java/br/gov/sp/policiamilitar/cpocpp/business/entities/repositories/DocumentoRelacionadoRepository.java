package br.gov.sp.policiamilitar.cpocpp.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.policiamilitar.cpocpp.business.entities.DocumentoRelacionado;


@Repository
@Transactional
public interface DocumentoRelacionadoRepository extends CrudRepository<DocumentoRelacionado, Long> {
    
}
