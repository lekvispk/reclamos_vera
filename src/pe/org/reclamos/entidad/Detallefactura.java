package pe.org.reclamos.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * The persistent class for the detallefactura database table.
 * 
 */
@Entity
@Table(name="detallefactura")
public class Detallefactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDetalleFactura;

	private int cantidad;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private int estado;

	private BigDecimal precio;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to CapacitacionItem
	//@OneToMany(mappedBy="detallefactura",fetch=FetchType.EAGER)
	//private Set<CapacitacionItem> capacitacionItems;

	//bi-directional many-to-one association to Factura
    @ManyToOne
	@JoinColumn(name="idFactura")
	private Factura factura;

	//bi-directional many-to-one association to Producto
    @ManyToOne
	@JoinColumn(name="idProducto")
	private Producto producto;

	//bi-directional many-to-one association to ItemsReclamo
	//@OneToMany(mappedBy="detallefactura",fetch=FetchType.EAGER)
	//private Set<ItemsReclamo> itemsReclamos;

    public Detallefactura() {
    }

	public Detallefactura(Integer idDetalleFactura) {
		this.idDetalleFactura = idDetalleFactura; 
	}

	public int getIdDetalleFactura() {
		return this.idDetalleFactura;
	}

	public void setIdDetalleFactura(int idDetalleFactura) {
		this.idDetalleFactura = idDetalleFactura;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/*public Set<CapacitacionItem> getCapacitacionItems() {
		return this.capacitacionItems;
	}

	public void setCapacitacionItems(Set<CapacitacionItem> capacitacionItems) {
		this.capacitacionItems = capacitacionItems;
	}*/
	
	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	/*public Set<ItemsReclamo> getItemsReclamos() {
		return this.itemsReclamos;
	}

	public void setItemsReclamos(Set<ItemsReclamo> itemsReclamos) {
		this.itemsReclamos = itemsReclamos;
	}*/
	
	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}