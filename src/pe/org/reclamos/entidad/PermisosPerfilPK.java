package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The primary key class for the permisos_perfil database table.
 * 
 */
@Embeddable
public class PermisosPerfilPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idPermiso;

	private int idPerfil;

    public PermisosPerfilPK() {
    }
	public int getIdPermiso() {
		return this.idPermiso;
	}
	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}
	public int getIdPerfil() {
		return this.idPerfil;
	}
	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PermisosPerfilPK)) {
			return false;
		}
		PermisosPerfilPK castOther = (PermisosPerfilPK)other;
		return 
			(this.idPermiso == castOther.idPermiso)
			&& (this.idPerfil == castOther.idPerfil);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPermiso;
		hash = hash * prime + this.idPerfil;
		
		return hash;
    }
	
}