package pe.org.reclamos.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.UsuarioDAO;
import pe.org.reclamos.entidad.Usuario;

@Repository
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDAO {

	@Autowired
	public UsuarioDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public List<Usuario> listarUsuario(Usuario usuario) {
		return null;
	}

}
