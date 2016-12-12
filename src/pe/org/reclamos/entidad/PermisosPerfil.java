package pe.org.reclamos.entidad;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the permisos_perfil database table.
 * 
 */
@Entity
@Table(name="permisos_perfil")
public class PermisosPerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PermisosPerfilPK id;

	private int estado;

	//bi-directional many-to-one association to Permiso
    @ManyToOne
	@JoinColumn(name="idPermiso", updatable=false, insertable=false)
	private Permiso permiso;

	//bi-directional many-to-one association to Perfil
    @ManyToOne
	@JoinColumn(name="idPerfil", updatable=false, insertable=false)
	private Perfil perfil;

    public PermisosPerfil() {
    }

	public PermisosPerfilPK getId() {
		return this.id;
	}

	public void setId(PermisosPerfilPK id) {
		this.id = id;
	}
	
	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Permiso getPermiso() {
		return this.permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}
	
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	
}