package br.gov.sp.policiamilitar.cpocpp.business.entities.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.policiamilitar.cpocpp.business.entities.ProcessoCPOCPP;


@Repository
@Transactional
public interface ProcessoCPOCPPRepository extends CrudRepository<ProcessoCPOCPP, Long> {
	  @Query("select p from ProcessoCPOCPP p JOIN p.interessados t where t.nomeInteressado = ?")
	  public List<ProcessoCPOCPP> findByNomeInteressado(String nomeInteressado);
	  
	  @Query("select p from ProcessoCPOCPP p JOIN p.interessados t where t.reInteressado = ?")
	  public List<ProcessoCPOCPP> findByReInteressado(Long ReInteressado);
	  
	  @Query("select p from ProcessoCPOCPP p JOIN p.status t where t.idStatus = ?")
	  public List<ProcessoCPOCPP> findByIdStatus(Long idStatus);
	  
	  public List<ProcessoCPOCPP> findByIdSecCom(Long idSecCom);
}
