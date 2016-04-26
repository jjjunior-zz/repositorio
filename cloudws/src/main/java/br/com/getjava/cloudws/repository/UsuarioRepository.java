package br.com.getjava.cloudws.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.getjava.cloudws.domain.Usuario;

public class UsuarioRepository extends JpaRepository<Usuario, Integer> {

	private EntityManager entityManager;

	private UsuarioRepository(EntityManager entityManager) {
		super(Usuario.class);
		this.entityManager = entityManager;
	}

	public static UsuarioRepository newInstance(EntityManager entityManager) {
		return new UsuarioRepository(entityManager);
	}

	public void incluirUsuario(Usuario usuario) {
		begin();
		persist(usuario);
		flush();
	}

	public Usuario buscarUsuarioPorEmail(String email) {

		TypedQuery<Usuario> query = this.entityManager.createQuery("select u from Usuario u where u.email = :email", Usuario.class);

		return querySingle(query, ParametroQuery.with("email", email).parametros());
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

}
