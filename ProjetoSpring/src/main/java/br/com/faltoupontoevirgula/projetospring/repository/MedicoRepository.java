package br.com.faltoupontoevirgula.projetospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.faltoupontoevirgula.projetospring.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}