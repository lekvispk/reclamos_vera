package pe.org.reclamos.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * The persistent class for the tb_factura database table.
 * 
 */
@Entity
@Table(name="tb_factura")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idFactura;

	private String descripcion;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecFactura;

	@Transient
	private Date fecFacturaFin;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecRegistro;
	
	private String numero;
	
	private Double monto;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="idProveedor")
	private Proveedor tbProveedor;

	public Factura() {
		cliente = new Cliente();
	}

	public Factura( Cliente cliente) {
		this.cliente=cliente;
	}
	
	public Long getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
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

	public Date getFecFactura() {
		return this.fecFactura;
	}

	public void setFecFactura(Date fecFactura) {
		this.fecFactura = fecFactura;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente tbCliente) {
		this.cliente = tbCliente;
	}

	public Proveedor getTbProveedor() {
		return this.tbProveedor;
	}

	public void setTbProveedor(Proveedor tbProveedor) {
		this.tbProveedor = tbProveedor;
	}

	public Date getFecRegistro() {
		return fecRegistro;
	}

	public void setFecRegistro(Date fecRegistro) {
		this.fecRegistro = fecRegistro;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFecFacturaFin() {
		return fecFacturaFin;
	}

	public void setFecFacturaFin(Date fecFacturaFin) {
		this.fecFacturaFin = fecFacturaFin;
	}

	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }

}