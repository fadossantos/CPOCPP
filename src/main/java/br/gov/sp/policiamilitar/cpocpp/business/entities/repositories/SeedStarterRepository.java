package br.gov.sp.policiamilitar.cpocpp.business.entities.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.policiamilitar.cpocpp.business.entities.SeedStarter;


@Repository
@Transactional
public interface SeedStarterRepository extends CrudRepository<SeedStarter, Long> {
    
}
