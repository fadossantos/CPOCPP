package br.gov.sp.policiamilitar.cpocpp.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.policiamilitar.cpocpp.business.entities.TipoDocumento;


@Repository
@Transactional
public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, Long> {
	
	public TipoDocumento findByDescTipoDocumento(String descTipoDocumento);
    
}
