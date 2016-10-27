package pe.org.reclamos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.PermisoDAO;
import pe.org.reclamos.entidad.Permiso;
import pe.org.reclamos.service.PermisoService;

@Service
public class PermisoServiceImpl implements PermisoService {

	@Autowired
	PermisoDAO permisoDAO;
	
	@Override
	public List<Permiso> listarPermisos() {
		return permisoDAO.listarPermisos();
	}

}
