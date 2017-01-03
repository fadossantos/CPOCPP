package br.gov.sp.policiamilitar.cpocpp.business.entities.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.policiamilitar.cpocpp.business.entities.Permissoes;

@Transactional
public interface PermissoesRepository extends CrudRepository<Permissoes, Long> {
	public List<Permissoes> findByCpf(long cpf);
}
