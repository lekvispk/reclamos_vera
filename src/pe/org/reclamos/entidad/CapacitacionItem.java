package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;


/**
 * The persistent class for the capacitacion_items database table.
 * 
 */
@Entity
@Table(name="capacitacion_items")
public class CapacitacionItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCapacitacionItem;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String detalle;

	private int estado;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Detallefactura
    @ManyToOne
	@JoinColumn(name="idDetalleFactura")
	private Detallefactura detallefactura;

	//bi-directional many-to-one association to Capacitacion
    @ManyToOne
	@JoinColumn(name="idCapacitacion")
	private Capacitacion capacitacion;

    public CapacitacionItem() {
    }

	public int getIdCapacitacionItem() {
		return this.idCapacitacionItem;
	}

	public void setIdCapacitacionItem(int idCapacitacionItem) {
		this.idCapacitacionItem = idCapacitacionItem;
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

	public Detallefactura getDetallefactura() {
		return this.detallefactura;
	}

	public void setDetallefactura(Detallefactura detallefactura) {
		this.detallefactura = detallefactura;
	}
	
	public Capacitacion getCapacitacion() {
		return this.capacitacion;
	}

	public void setCapacitacion(Capacitacion capacitacion) {
		this.capacitacion = capacitacion;
	}
	
	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}