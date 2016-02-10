package br.com.bluesoft.votacao.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.dao.DAO;
import br.com.bluesoft.votacao.dao.ParametroDaConsulta;
import br.com.bluesoft.votacao.domain.Usuario;
import br.com.bluesoft.votacao.exception.ModeloException;

@Repository
public class UsuarioRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private DAO<Usuario, Long> daoUsuaio;	
	
	@PostConstruct
	public void init(){
		this.daoUsuaio = DAO.newInstance(this.entityManager, Usuario.class);
	}
	
	public Usuario buscarUsuarioPorIndice(Long indice){		
		return this.daoUsuaio.selecionarPeloIndice(indice);
	}
	
	public List<Usuario> buscarUsuarioPorEmail(String email){		
		return this.daoUsuaio.selecionarComParametro(Usuario.SELECIONAR_POR_EMAIL,ParametroDaConsulta.com("email", email).parametros());
	}
	
	public List<Usuario> buscarTodosUsuarios(){		
		return this.daoUsuaio.selecionar(Usuario.TUDO);
	}
	
	@Transactional
	public void incluirUsuario(Usuario usuario) throws ModeloException{ 		
		this.daoUsuaio.inserir(usuario);
	}
	
	@Transactional
	public void excluirUsuario(Usuario usuario) throws ModeloException{
		this.daoUsuaio.deletar(usuario);			
	}
	
	@Transactional
	public void atualizarUsuario(Usuario usuario) throws ModeloException{
		this.daoUsuaio.alterar(usuario);			
	}
}
