package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Set;


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

	private int estado;

	private String permiso;

	//bi-directional many-to-one association to PermisosPerfil
	@OneToMany(mappedBy="permiso")
	private Set<PermisosPerfil> permisosPerfils;

	//bi-directional many-to-one association to PermisosUsuario
	@OneToMany(mappedBy="permiso")
	private Set<PermisosUsuario> permisosUsuarios;

    public Permiso() {
    }

	public int getIdPermiso() {
		return this.idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
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
	
	public Set<PermisosUsuario> getPermisosUsuarios() {
		return this.permisosUsuarios;
	}

	public void setPermisosUsuarios(Set<PermisosUsuario> permisosUsuarios) {
		this.permisosUsuarios = permisosUsuarios;
	}

	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}