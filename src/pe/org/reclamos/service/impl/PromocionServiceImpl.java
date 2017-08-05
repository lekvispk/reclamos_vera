package pe.org.reclamos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.PromocionDAO;
import pe.org.reclamos.entidad.Promocion;
import pe.org.reclamos.service.PromocionService;

@Service
public class PromocionServiceImpl implements PromocionService {

	@Autowired
	private PromocionDAO promocionDAO;
	
	@Override
	public List<Promocion> listarPromociones() {
		return promocionDAO.listarPromociones();
	}

	@Override
	public Promocion obtenerPromocion(Promocion promocion) {
		return promocionDAO.obtenerPromocion(promocion);
	}

}
