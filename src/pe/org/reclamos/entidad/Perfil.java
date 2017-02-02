package pe.org.reclamos.entidad;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@Table(name="perfil")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idPerfil;

	private int estado;

	private String perfil;

	@Transient
	private List<Permiso> listaPermisos;
	
	//bi-directional many-to-one association to PermisosPerfil
	@OneToMany(mappedBy="perfil")
	private Set<PermisosPerfil> permisosPerfils;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="perfil")
	private Set<Usuario> usuarios;

    public Perfil() {
    }

	public Long getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Set<PermisosPerfil> getPermisosPerfils() {
		return this.permisosPerfils;
	}

	public void setPermisosPerfils(Set<PermisosPerfil> permisosPerfils) {
		this.permisosPerfils = permisosPerfils;
	}
	
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Permiso> getListaPermisos() {
		return listaPermisos;
	}

	public void setListaPermisos(List<Permiso> listaPermisos) {
		this.listaPermisos = listaPermisos;
	}

	@Override
	public String toString() {
		return "Perfil [idPerfil=" + idPerfil + ", perfil=" + perfil + "]";
	}

}