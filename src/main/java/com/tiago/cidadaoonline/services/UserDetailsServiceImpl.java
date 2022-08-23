package com.tiago.cidadaoonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tiago.cidadaoonline.domain.Usuario;
import com.tiago.cidadaoonline.repositories.UsuarioRepository;
import com.tiago.cidadaoonline.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Usuario cli = repo.findByCpf(cpf);
		if (cli == null) {
			throw new UsernameNotFoundException(cpf);
		}
		return new UserSS(cli.getId(), cli.getCpf(), cli.getSenha(), cli.getPerfis());
	}
}