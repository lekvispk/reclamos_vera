package pe.org.reclamos.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.PermisoDAO;
import pe.org.reclamos.entidad.Permiso;

@Repository
public class PermisoDAOImpl extends HibernateDaoSupport implements PermisoDAO {

	@Autowired
	public PermisoDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Permiso> listarPermisos() {
		
		return this.getHibernateTemplate().find("from Permiso");
		
	}

}
