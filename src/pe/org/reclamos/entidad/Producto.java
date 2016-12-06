package pe.org.reclamos.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name="producto")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProducto;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String descripcion;

	private String direProducto;

	private int estado;

    @Temporal( TemporalType.TIMESTAMP)
	private Date fecProducto;

	private BigDecimal pesoProducto;

	private String skuProducto;

	private int tipoProducto;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to DetalleDevolucion
	//@OneToMany(mappedBy="producto")
    @Transient
	private Set<DetalleDevolucion> detalleDevolucions;

	//bi-directional many-to-one association to Detallefactura
	//@OneToMany(mappedBy="producto")
    @Transient
	private Set<Detallefactura> detallefacturas;

    public Producto() {
    }

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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

	public String getDireProducto() {
		return this.direProducto;
	}

	public void setDireProducto(String direProducto) {
		this.direProducto = direProducto;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecProducto() {
		return this.fecProducto;
	}

	public void setFecProducto(Date fecProducto) {
		this.fecProducto = fecProducto;
	}

	public BigDecimal getPesoProducto() {
		return this.pesoProducto;
	}

	public void setPesoProducto(BigDecimal pesoProducto) {
		this.pesoProducto = pesoProducto;
	}

	public String getSkuProducto() {
		return this.skuProducto;
	}

	public void setSkuProducto(String skuProducto) {
		this.skuProducto = skuProducto;
	}

	public int getTipoProducto() {
		return this.tipoProducto;
	}

	public void setTipoProducto(int tipoProducto) {
		this.tipoProducto = tipoProducto;
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
	
	public Set<Detallefactura> getDetallefacturas() {
		return this.detallefacturas;
	}

	public void setDetallefacturas(Set<Detallefactura> detallefacturas) {
		this.detallefacturas = detallefacturas;
	}

}