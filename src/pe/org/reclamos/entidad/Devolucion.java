package pe.org.reclamos.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the devolucion database table.
 * 
 */
@Entity
@Table(name="devolucion")
public class Devolucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idDevolucion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="fecha_autorizacion")
	private Date fechaAutorizacion;
    
    @Lob()
	private String detalle;
    
    @Column(name="numero_acta")
    private String numeroActa;

	private int estado;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to DetalleDevolucion
	//@OneToMany(mappedBy="devolucion")
    @Transient
	private Set<DetalleDevolucion> detalleDevolucions;

	//bi-directional many-to-one association to Reclamo
    @ManyToOne
	@JoinColumn(name="idReclamo")
	private Reclamo reclamo;

    public Devolucion() {
    }

	public Integer getIdDevolucion() {
		return this.idDevolucion;
	}

	public void setIdDevolucion(Integer idDevolucion) {
		this.idDevolucion = idDevolucion;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<DetalleDevolucion> getDetalleDevolucions() {
		return this.detalleDevolucions;
	}

	public void setDetalleDevolucions(Set<DetalleDevolucion> detalleDevolucions) {
		this.detalleDevolucions = detalleDevolucions;
	}
	
	public Reclamo getReclamo() {
		return this.reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public String getNumeroActa() {
		return numeroActa;
	}

	public void setNumeroActa(String numeroActa) {
		this.numeroActa = numeroActa;
	}
	
}