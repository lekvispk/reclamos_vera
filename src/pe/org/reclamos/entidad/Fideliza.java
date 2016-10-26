package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;


/**
 * The persistent class for the fideliza database table.
 * 
 */
@Entity
@Table(name="fideliza")
public class Fideliza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idFideliza;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String descripcion;

	private int estado;

    @Temporal( TemporalType.TIMESTAMP)
	private Date fecFideliza;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Promocion
    @ManyToOne
	@JoinColumn(name="idPromocion")
	private Promocion promocion;

	//bi-directional many-to-one association to Reclamo
    @ManyToOne
	@JoinColumn(name="idReclamo")
	private Reclamo reclamo;

    public Fideliza() {
    }

	public int getIdFideliza() {
		return this.idFideliza;
	}

	public void setIdFideliza(int idFideliza) {
		this.idFideliza = idFideliza;
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

	public Date getFecFideliza() {
		return this.fecFideliza;
	}

	public void setFecFideliza(Date fecFideliza) {
		this.fecFideliza = fecFideliza;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Promocion getPromocion() {
		return this.promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
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