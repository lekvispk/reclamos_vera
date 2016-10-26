package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the detalle_devolucion database table.
 * 
 */
@Entity
@Table(name="detalle_devolucion")
public class DetalleDevolucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDetalleDevolucion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="direccion_entrega")
	private String direccionEntrega;

	private int estado;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_entrega")
	private Date fechaEntrega;

	@Column(name="hora_entrega")
	private Time horaEntrega;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Devolucion
    @ManyToOne
	@JoinColumn(name="idDevolucion")
	private Devolucion devolucion;

	//bi-directional many-to-one association to Producto
    @ManyToOne
	@JoinColumn(name="idProducto")
	private Producto producto;

	//bi-directional many-to-one association to Despachador
    @ManyToOne
	@JoinColumn(name="idDespachador")
	private Despachador despachador;

    public DetalleDevolucion() {
    }

	public int getIdDetalleDevolucion() {
		return this.idDetalleDevolucion;
	}

	public void setIdDetalleDevolucion(int idDetalleDevolucion) {
		this.idDetalleDevolucion = idDetalleDevolucion;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDireccionEntrega() {
		return this.direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Time getHoraEntrega() {
		return this.horaEntrega;
	}

	public void setHoraEntrega(Time horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Devolucion getDevolucion() {
		return this.devolucion;
	}

	public void setDevolucion(Devolucion devolucion) {
		this.devolucion = devolucion;
	}
	
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Despachador getDespachador() {
		return this.despachador;
	}

	public void setDespachador(Despachador despachador) {
		this.despachador = despachador;
	}
	
	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}