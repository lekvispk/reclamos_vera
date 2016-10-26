package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * The persistent class for the permisos_usuario database table.
 * 
 */
@Entity
@Table(name="permisos_usuario")
public class PermisosUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PermisosUsuarioPK id;

	private int estado;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="idUsuario", updatable=false, insertable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Permiso
    @ManyToOne
	@JoinColumn(name="idPermiso", updatable=false, insertable=false)
	private Permiso permiso;

    public PermisosUsuario() {
    }

	public PermisosUsuarioPK getId() {
		return this.id;
	}

	public void setId(PermisosUsuarioPK id) {
		this.id = id;
	}
	
	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Permiso getPermiso() {
		return this.permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}