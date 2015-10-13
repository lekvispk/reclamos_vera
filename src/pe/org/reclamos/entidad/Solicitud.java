package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_solicitud database table.
 * 
 */
@Entity
@Table(name="tb_solicitud")
@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSolicitud;

	private String descripcion;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecSolicitud;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente tbCliente;

	public Solicitud() {
	}

	public int getIdSolicitud() {
		return this.idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
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

	public Cliente getTbCliente() {
		return this.tbCliente;
	}

	public void setTbCliente(Cliente tbCliente) {
		this.tbCliente = tbCliente;
	}

}