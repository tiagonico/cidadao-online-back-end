package com.tiago.cidadaoonline.repositories;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tiago.cidadaoonline.domain.Usuario;

@Configurable
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Transactional(readOnly = true)
	Usuario findByCpf(String cpf);
	
	
}
