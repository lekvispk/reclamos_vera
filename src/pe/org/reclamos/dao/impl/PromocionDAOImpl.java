package pe.org.reclamos.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.PromocionDAO;
import pe.org.reclamos.entidad.Promocion;

@Repository
public class PromocionDAOImpl extends HibernateDaoSupport  implements PromocionDAO {

	@Autowired
	public PromocionDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Promocion> listarPromociones() {
		return this.getHibernateTemplate().find("from Promocion where estado=1");
	}

}
