package com.tiago.cidadaoonline.services;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiago.cidadaoonline.domain.Solicitacao;
import com.tiago.cidadaoonline.domain.TipoSolicitacao;
import com.tiago.cidadaoonline.domain.Usuario;
import com.tiago.cidadaoonline.domain.enums.Perfil;
import com.tiago.cidadaoonline.domain.enums.StatusSolicitacao;
import com.tiago.cidadaoonline.repositories.SolicitacaoRepository;
import com.tiago.cidadaoonline.repositories.TipoSolicitacaoRepository;
import com.tiago.cidadaoonline.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private TipoSolicitacaoRepository tipoSolicitacaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	

	public void instantiateTestDatabase() throws ParseException {

		TipoSolicitacao ts1 = new TipoSolicitacao(null, "Iluminação pública","https://cidadao-online.s3.sa-east-1.amazonaws.com/iluminacao-publica.jpeg");
		TipoSolicitacao ts2 = new TipoSolicitacao(null, "Manutenção de Semáforo","https://cidadao-online.s3.sa-east-1.amazonaws.com/manutencao-de-semaforo.png");
		TipoSolicitacao ts3 = new TipoSolicitacao(null, "Terreno baldio","https://cidadao-online.s3.sa-east-1.amazonaws.com/terreno-baldio.jpg");
		TipoSolicitacao ts4 = new TipoSolicitacao(null, "Tapa buraco","https://cidadao-online.s3.sa-east-1.amazonaws.com/tapa-buraco.jpg");
		TipoSolicitacao ts5 = new TipoSolicitacao(null, "Descarte irregular de entulho","https://cidadao-online.s3.sa-east-1.amazonaws.com/descarte-irregular-de-lixo.jpg");

		tipoSolicitacaoRepository.saveAll(Arrays.asList(ts1,ts2,ts3,ts4,ts5));


		System.out.println("AQQUIIIIIIIII");
		

		Usuario user1 = new Usuario(null, "Tiago Silva", "tiagosilva0512@gmail.com", "12887548760", pe.encode("123"));
		Usuario user2 = new Usuario(null, "Maria Silva", "mariasilva@gmail.com", "11111111111", pe.encode("123"));
		user2.addPerfil(Perfil.ADMIN);

		

		Solicitacao solicit1 = new Solicitacao(null, "Teste","Teste", "-20.349166954953137", "-40.38818120794794", ZonedDateTime.now(), ts1, user1, StatusSolicitacao.SOLICITADO,null);
		Solicitacao solicit2 = new Solicitacao(null, "Teste","Teste", "-20.349166954953137", "-40.38818120794794", ZonedDateTime.now(), ts2, user1, StatusSolicitacao.EM_ANDAMENTO,null);
		Solicitacao solicit3 = new Solicitacao(null, "Teste","Teste", "-20.349166954953137", "-40.38818120794794", ZonedDateTime.now(), ts3, user1, StatusSolicitacao.CONCLUIDO,null);
		Solicitacao solicit4 = new Solicitacao(null, "Teste","Teste", "-20.349166954953137", "-40.38818120794794", ZonedDateTime.now(), ts4, user1, StatusSolicitacao.REJEITADO,null);
		
		user1.getSolicitacoes().addAll(Arrays.asList(solicit1,solicit2,solicit3,solicit4));
		
		usuarioRepository.saveAll(Arrays.asList(user1,user2));		
		solicitacaoRepository.saveAll(Arrays.asList(solicit1,solicit2,solicit3,solicit4));
		
		
		
	}
}