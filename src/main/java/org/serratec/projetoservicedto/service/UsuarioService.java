package org.serratec.projetoservicedto.service;

import java.util.List;
import java.util.Optional;

import org.serratec.projetoservicedto.CriptografiaService;
import org.serratec.projetoservicedto.config.MailConfig;
import org.serratec.projetoservicedto.dominio.Usuario;
import org.serratec.projetoservicedto.exception.EmailException;
import org.serratec.projetoservicedto.respositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CriptografiaService criptografiaService;
	
	@Autowired
	private MailConfig mailConf;
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> getById(long id) {
		return usuarioRepository.findById(id);
	}
	
	public Usuario inserir(Usuario user) throws EmailException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(user.getEmail());
		if(usuario.isPresent()) {
			throw new EmailException("Usuário com este e-mail já existe no cadastro.");			
		}		
		user.setSenha(criptografiaService.criptografar(user.getSenha()));
		Usuario usuarioSalvo =  usuarioRepository.save(user);
		//mailConf.sendMail(usuarioSalvo.getEmail(), "Cadastro de usuário", usuarioSalvo.toString());
		return usuarioSalvo;
		
	}
}

