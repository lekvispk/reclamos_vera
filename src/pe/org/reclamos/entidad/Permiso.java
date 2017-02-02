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
	private Integer idPermiso;

	private String detalle;

	private int estado;

	private String permiso;

	//bi-directional many-to-one association to PermisosPerfil
	@OneToMany(mappedBy="permiso")
	private Set<PermisosPerfil> permisosPerfils;

    public Permiso() {
    }
    
    public Permiso(Integer idPermiso ) {
    	this.idPermiso = idPermiso;
    }

	public Integer getIdPermiso() {
		return this.idPermiso;
	}

	public void setIdPermiso(Integer idPermiso) {
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
	        return false;
	    }
		if (!Permiso.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
		final Permiso other = (Permiso) obj;
	    if ((this.permiso == null) ? (other.permiso != null) : !this.permiso.equals(other.permiso)) {
	        return false;
	    }
	    if (this.idPermiso != other.idPermiso) {
	        return false;
	    }
	    return true;
	}

	@Override
	public String toString() {
		return "Permiso [idPermiso=" + idPermiso + ", detalle=" + detalle + ", permiso=" + permiso + "]";
	}
	
	
}