package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the permisos_usuario database table.
 * 
 */
@Embeddable
public class PermisosUsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idUsuario;

	private int idPermiso;

    public PermisosUsuarioPK() {
    }
	public int getIdUsuario() {
		return this.idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdPermiso() {
		return this.idPermiso;
	}
	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PermisosUsuarioPK)) {
			return false;
		}
		PermisosUsuarioPK castOther = (PermisosUsuarioPK)other;
		return 
			(this.idUsuario == castOther.idUsuario)
			&& (this.idPermiso == castOther.idPermiso);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUsuario;
		hash = hash * prime + this.idPermiso;
		
		return hash;
    }
}