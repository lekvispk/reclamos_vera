package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;


/**
 * The persistent class for the indemnizacion database table.
 * 
 */
@Entity
@Table(name="indemnizacion")
public class Indemnizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idIndemnizacion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private int estado;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_indemnizacion")
	private Date fechaIndemnizacion;

	@Column(name="total_indemnizacion")
	private double totalIndemnizacion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Reclamo
    @ManyToOne
	@JoinColumn(name="idReclamo")
	private Reclamo reclamo;

    public Indemnizacion() {
    }

	public int getIdIndemnizacion() {
		return this.idIndemnizacion;
	}

	public void setIdIndemnizacion(int idIndemnizacion) {
		this.idIndemnizacion = idIndemnizacion;
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

	public Date getFechaIndemnizacion() {
		return this.fechaIndemnizacion;
	}

	public void setFechaIndemnizacion(Date fechaIndemnizacion) {
		this.fechaIndemnizacion = fechaIndemnizacion;
	}

	public double getTotalIndemnizacion() {
		return this.totalIndemnizacion;
	}

	public void setTotalIndemnizacion(double totalIndemnizacion) {
		this.totalIndemnizacion = totalIndemnizacion;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Reclamo getReclamo() {
		return this.reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}