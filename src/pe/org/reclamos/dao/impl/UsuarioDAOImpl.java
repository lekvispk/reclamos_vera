package pe.org.reclamos.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.reclamos.dao.UsuarioDAO;
import pe.org.reclamos.entidad.Factura;
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
		try {
			return (Usuario) this.getHibernateTemplate().find("from Usuario u where idUsuario = ? ", idUsuario).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void registrar(Usuario usuario) {
		this.getHibernateTemplate().saveOrUpdate( usuario );
	}

}
