package pe.org.reclamos.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.PerfilDAO;
import pe.org.reclamos.entidad.Perfil;
import pe.org.reclamos.entidad.Permiso;
import pe.org.reclamos.entidad.PermisosPerfil;
@Repository
public class PerfilDAOImpl extends HibernateDaoSupport implements PerfilDAO {

	@Autowired
	public PerfilDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> listarPerfiles() {
		return this.getHibernateTemplate().find("from Perfil where estado=1");
	}

	@Override
	public void eliminar(Long id) {
		logger.debug("eliminar " + id );
		this.getHibernateTemplate().bulkUpdate("update Perfil u set estado=0 where idPerfil = ?  ", id);
	}

	@Override
	public void registrar(Perfil perfil) {
		//this.getHibernateTemplate().saveOrUpdate( perfil );
		this.getHibernateTemplate().save( perfil );
	}

	@Override
	public void modificar(Perfil perfil) {
		this.getHibernateTemplate().update( perfil );
	}

	@Override
	public Perfil obtener(Long IdPerfil) {
		try {
			return (Perfil) this.getHibernateTemplate()
								.find("from Perfil u where IdPerfil = ? ", IdPerfil)
								.get(0);
		} catch (Exception e) {
			logger.debug( e.getMessage() );
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permiso> obtenerListaPermisos(Long idPerfil) {
		logger.debug( " obtenerListaPermisos idPerfil=" + idPerfil);
		List<Permiso> lista =this.getHibernateTemplate()
				.find("select p from Permiso p join fetch p.permisosPerfils pp where pp.perfil.idPerfil = ? ", idPerfil);
		logger.debug( " elementos " + lista.size());
		for(Permiso p : lista){
			logger.debug( p.getPermiso()  + " " + p.getIdPermiso());	
		}
		return lista;
	}

}
