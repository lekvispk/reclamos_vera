package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.sql.Time;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the capacitacion database table.
 * 
 */
@Entity
@Table(name="capacitacion")
public class Capacitacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCapacitacion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private int estado;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_capacitacion")
	private Date fechaCapacitacion;

	@Column(name="hora_capacitacion")
	private Time horaCapacitacion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Capacitador
    @ManyToOne
	@JoinColumn(name="idCapacitador")
	private Capacitador capacitador;

	//bi-directional many-to-one association to Factura
    @ManyToOne
	@JoinColumn(name="idFactura")
	private Factura factura;

	//bi-directional many-to-one association to CapacitacionItem
	//@OneToMany(mappedBy="capacitacion")
    @Transient
	private Set<CapacitacionItem> capacitacionItems;

    public Capacitacion() {
    }

	public Capacitacion(Integer idCapacitacion) {
		this.idCapacitacion = idCapacitacion;
	}

	public int getIdCapacitacion() {
		return this.idCapacitacion;
	}

	public void setIdCapacitacion(int idCapacitacion) {
		this.idCapacitacion = idCapacitacion;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaCapacitacion() {
		return this.fechaCapacitacion;
	}

	public void setFechaCapacitacion(Date fechaCapacitacion) {
		this.fechaCapacitacion = fechaCapacitacion;
	}

	public Time getHoraCapacitacion() {
		return this.horaCapacitacion;
	}

	public void setHoraCapacitacion(Time horaCapacitacion) {
		this.horaCapacitacion = horaCapacitacion;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Capacitador getCapacitador() {
		return this.capacitador;
	}

	public void setCapacitador(Capacitador capacitador) {
		this.capacitador = capacitador;
	}
	
	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public Set<CapacitacionItem> getCapacitacionItems() {
		return this.capacitacionItems;
	}

	public void setCapacitacionItems(Set<CapacitacionItem> capacitacionItems) {
		this.capacitacionItems = capacitacionItems;
	}
	
	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}