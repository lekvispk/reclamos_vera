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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@Table(name="factura")
public class Factura implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idFactura;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

    @Temporal( TemporalType.TIMESTAMP)
	private Date emision;
    
    @Transient
    private Date emisionFin;

	private int estado;

	private BigDecimal monto;

	private String numero;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to Capacitacion
	//@OneToMany(mappedBy="factura",fetch=FetchType.LAZY)
    @Transient
	private Set<Capacitacion> capacitacions;

	//bi-directional many-to-one association to Detallefactura
	//@OneToMany(mappedBy="factura",fetch=FetchType.LAZY)
	@Transient
	private Set<Detallefactura> detallefacturas;

	//bi-directional many-to-one association to Cliente
    @ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Proveedor
    @ManyToOne
	@JoinColumn(name="idProveedor")
	private Proveedor proveedor;

    //bi-directional many-to-one association to Reclamo
	//@OneToMany(mappedBy="factura")
    @Transient
	private Set<Reclamo> reclamos;
    
    //@Column(insertable=false,updatable=false)
    @Transient
    private Integer idFideliza;
    
    //No corresponde a la factura pero se ha puesto aqui para usarlo en la pantalla de autorizar cambio de producto
    @Transient
    private Integer numeroActa;
    
    public Factura() {
    }

    public Factura(Cliente cliente) {
    	this.cliente = cliente;
    }
    
	public Long getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getEmision() {
		return this.emision;
	}

	public void setEmision(Date emision) {
		this.emision = emision;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<Capacitacion> getCapacitacions() {
		return this.capacitacions;
	}

	public void setCapacitacions(Set<Capacitacion> capacitacions) {
		this.capacitacions = capacitacions;
	}
	
	public Set<Detallefactura> getDetallefacturas() {
		return this.detallefacturas;
	}

	public void setDetallefacturas(Set<Detallefactura> detallefacturas) {
		this.detallefacturas = detallefacturas;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	 public Date getEmisionFin() {
		return emisionFin;
	}

	public void setEmisionFin(Date emisionFin) {
		this.emisionFin = emisionFin;
	}

	public Set<Reclamo> getReclamos() {
		return this.reclamos;
	}

	public void setReclamos(Set<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}
	
	
	public Integer getIdFideliza() {
		return idFideliza;
	}

	public void setIdFideliza(Integer idFideliza) {
		this.idFideliza = idFideliza;
	}

	public Integer getNumeroActa() {
		return numeroActa;
	}

	public void setNumeroActa(Integer numeroActa) {
		this.numeroActa = numeroActa;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", estado=" + estado + ", monto=" + monto + ", numero=" + numero + ", cliente="  + cliente 
				+ "]";
	}
	
	

}