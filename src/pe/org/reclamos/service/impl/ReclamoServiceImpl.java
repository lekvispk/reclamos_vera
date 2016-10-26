package pe.org.reclamos.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.ReclamoDAO;
import pe.org.reclamos.entidad.Reclamo;
import pe.org.reclamos.service.ReclamoService;

@Service
public class ReclamoServiceImpl implements ReclamoService {

	@Autowired
	private ReclamoDAO reclamoDAO;
	
	@Override
	public void registrar(Reclamo reclamo) {
		if(reclamo.getFecReclamo()==null){
			reclamo.setFecReclamo(new Date());
			//auditoria
			reclamo.setFecReclamo( new Date());
			//TODO validar cuando se registra fecha de vencimiento
		}
		reclamoDAO.registrar(reclamo);
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
