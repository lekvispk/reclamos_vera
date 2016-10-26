package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;


/**
 * The persistent class for the solicitud database table.
 * 
 */
@Entity
@Table(name="solicitud")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSolicitud;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String descripcion;

	private int estado;

    @Temporal( TemporalType.TIMESTAMP)
	private Date fecSolicitud;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Cliente
    @ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;

    public Solicitud() {
    }

	public int getIdSolicitud() {
		return this.idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecSolicitud() {
		return this.fecSolicitud;
	}

	public void setFecSolicitud(Date fecSolicitud) {
		this.fecSolicitud = fecSolicitud;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}