package com.tiago.cidadaoonline.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiago.cidadaoonline.domain.Solicitacao;
import com.tiago.cidadaoonline.domain.Usuario;
import com.tiago.cidadaoonline.domain.enums.Perfil;
import com.tiago.cidadaoonline.dto.UsuarioNewDTO;
import com.tiago.cidadaoonline.repositories.SolicitacaoRepository;
import com.tiago.cidadaoonline.repositories.UsuarioRepository;
import com.tiago.cidadaoonline.security.UserSS;
import com.tiago.cidadaoonline.services.exceptions.AuthorizationException;
import com.tiago.cidadaoonline.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public List<Usuario> findAll() {
		return repo.findAll();		
	}
	
	public boolean hasRoleGestor(String cpf) {
		Usuario user = repo.findByCpf(cpf);
		
		return user.hasRole("GESTOR");
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Usuario fromDTO(UsuarioNewDTO objDTO){
		return new Usuario(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),objDTO.getCpf(),pe.encode(objDTO.getSenha()));		
	}
	
	public Usuario find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public List<Solicitacao> findSolicitacaoByCpf(String cpf){
		
		Usuario user = repo.findByCpf(cpf);
		return solicitacaoRepository.findByUsuario(user);
	}
	
	public void acessoGestor(String cpf) {
		
		try {
			Usuario user = repo.findByCpf(cpf);
			user.addPerfil(Perfil.ADMIN);
			repo.save(user);
		} catch(Exception e) {
			return;
		}
		
	}

}
