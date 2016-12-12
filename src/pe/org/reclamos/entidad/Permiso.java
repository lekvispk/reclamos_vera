package pe.org.reclamos.entidad;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the permisos database table.
 * 
 */
@Entity
@Table(name="permisos")
public class Permiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPermiso;

	private String detalle;

	private int estado;

	private String permiso;

	//bi-directional many-to-one association to PermisosPerfil
	@OneToMany(mappedBy="permiso")
	private Set<PermisosPerfil> permisosPerfils;

    public Permiso() {
    }

	public int getIdPermiso() {
		return this.idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getPermiso() {
		return this.permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

	public Set<PermisosPerfil> getPermisosPerfils() {
		return this.permisosPerfils;
	}

	public void setPermisosPerfils(Set<PermisosPerfil> permisosPerfils) {
		this.permisosPerfils = permisosPerfils;
	}
	
}