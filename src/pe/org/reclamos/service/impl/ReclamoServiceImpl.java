package pe.org.reclamos.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.org.reclamos.dao.ReclamoDAO;
import pe.org.reclamos.entidad.ItemsReclamo;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.ReclamoService;

@Service
public class ReclamoServiceImpl implements ReclamoService {

	@Autowired
	private ReclamoDAO reclamoDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void registrar(Reclamo reclamo) throws Exception {
		//validar que no exista un reclamo con esa factura 
		
		Reclamo rec = new Reclamo();
		rec.setFactura( reclamo.getFactura() );
		List<Reclamo> lista = reclamoDAO.buscar( rec );
		if( lista.size() > 0){
			throw new Exception("Ya existe un reclamo con la factura indicada");
		}
		
		if(reclamo.getFecReclamo()==null){
			reclamo.setFecReclamo(new Date());
			//auditoria
			reclamo.setFecReclamo( new Date());
			//TODO validar cuando se registra fecha de vencimiento
		}
		
		reclamoDAO.registrar(reclamo);
		//registrar itemdetalle
		for(ItemsReclamo item : reclamo.getItemsReclamos()){
			System.out.println( "por insertar item ");
			item.setEstado( 1 );
			item.setReclamo( reclamo );
			reclamoDAO.registrar( item );		
		}
		
	}

	@Override
	public Reclamo obtener(Long reclamo) {
		return reclamoDAO.obtener(reclamo);
	}

	@Override
	public List<Reclamo> buscar(Reclamo reclamo) {
		return reclamoDAO.buscar(reclamo);
	}

	@Override
	public void eliminar(Long reclamo) {
		reclamoDAO.eliminar(reclamo);
	}

}
