package pe.org.reclamos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.org.reclamos.dao.UsuarioDAO;
import pe.org.reclamos.entidad.Authority;
import pe.org.reclamos.entidad.Cliente;
import pe.org.reclamos.entidad.Permiso;
import pe.org.reclamos.entidad.Persona;
import pe.org.reclamos.entidad.Usuario;
import pe.org.reclamos.utiles.Utiles;

@Repository
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDAO {

	@Autowired
	public UsuarioDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuarios(Usuario usuario) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		logger.debug(" buscar Factura() " );
				
		if(usuario !=null){
			logger.debug(" estado " + usuario.getEstado());
			
			if( usuario.getEstado() > 0 )
				criteria.add( Restrictions.eq("estado", usuario.getEstado() ) );
			else
				criteria.add( Restrictions.gt("estado", 0 ) );
			
			if( usuario.getIdUsuario() != null  ){
				criteria.add( Restrictions.eq("idUsuario", usuario.getIdUsuario() ) );
			}
			
			if( !Utiles.nullToBlank( usuario.getEmail() ).equals("")){
				criteria.add( Restrictions.ge("email", usuario.getEmail() ) );
			}
		}
		//criteria.addOrder( Order.desc("fechaIngreso") );
		//criteria.setFetchMode("capacitacions", FetchMode.JOIN);
		//criteria.setFetchMode("detallefacturas", FetchMode.JOIN);
		
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Usuario obtener(Integer idUsuario) {
		logger.debug("obtener idUsuario="+idUsuario);
		try {
			return (Usuario) this.getHibernateTemplate().find("from Usuario u where idUsuario = ? ", idUsuario).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void registrar(Usuario usuario) {
		logger.debug("registrar usuario - INI");
		this.getHibernateTemplate().saveOrUpdate( usuario );
	}

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void registrar(Persona persona) {
		logger.debug("registrar persona - INI");
		this.getHibernateTemplate().saveOrUpdate( persona );
	}

	@Override
	public Usuario obtenerUsuarioPorUsername(String username) {
		Query query = getSession().createQuery("from Usuario u where username = :id  ")
        .setString("id", username);
		Usuario h = (Usuario)query.uniqueResult();
		return h;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void registrarPermisos(Usuario usuario, List<Permiso> permisos) {
		for(Permiso p : permisos){
			logger.debug( p.getPermiso() + " " + usuario.getUsername() );
			Authority permiso = new Authority();
			permiso.setAuthority( p.getPermiso() );
			permiso.setUsername( usuario.getUsername() );
			this.getHibernateTemplate().saveOrUpdate( permiso );	
		}
	}

	@Override
	public void eliminar(Integer idUsuario) {
		this.getHibernateTemplate().bulkUpdate("update Usuario u set estado=0 where idUsuario= ?  ", idUsuario);
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		logger.debug("email a buscar = "+email);
		try {
			return (Usuario) this.getHibernateTemplate().find("from Usuario u where email = ? ", email).get(0);
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return null;
		}
	}

	@Override
	public Cliente obteneClientePorPersona(Integer idPersona) {
		try {
			return (Cliente) this.getHibernateTemplate().find("from Cliente u where persona.idPersona = ? ", idPersona).get(0);
		} catch (Exception e) {
			logger.error( e.getMessage() );
			return null;
		}
	}

	@Override
	public void eliminarPermisos(String username) {
		final String METHODNAME = "eliminarPermisos - ";
		logger.debug(METHODNAME + "INI");
		this.getHibernateTemplate().bulkUpdate("delete from Authority where username= ?  ", username);
		logger.debug(METHODNAME + "FIN");
	}

}
