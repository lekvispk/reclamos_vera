package pe.org.reclamos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.reclamos.dao.PerfilDAO;
import pe.org.reclamos.entidad.Perfil;
import pe.org.reclamos.service.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	PerfilDAO perfilDAO;
	
	@Override
	public List<Perfil> listarPerfiles() {
		return perfilDAO.listarPerfiles();
	}

	@Override
	public void eliminar(Long IdPerfil) {
		perfilDAO.eliminar(IdPerfil);
	}

	@Override
	public void registrar(Perfil perfil) {
		perfilDAO.registrar(perfil);
	}

	@Override
	public void modificar(Perfil perfil) {
		perfilDAO.modificar(perfil);
	}

	@Override
	public Perfil obtener(Long IdPerfil) {
		return perfilDAO.obtener(IdPerfil);
	}

}
