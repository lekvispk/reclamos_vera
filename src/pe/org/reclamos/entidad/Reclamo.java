package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the reclamo database table.
 * 
 */
@Entity
@Table(name="reclamo")
public class Reclamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idReclamo;
	
	private Long idCliente;

	private String asunto;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

    @Lob()
	private String descripcion;

	private int estado;

	@Transient
	private List<Integer> estados;
	
    @Temporal( TemporalType.TIMESTAMP)
	private Date fecReclamo;

	private String indemnizar;

    @Lob()
	private String mensaje;

	private int prioridad;

	private String respuesta;

    @Lob()
	private String solucion;

	private String tipoReclamo;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

    @Temporal( TemporalType.TIMESTAMP)
	private Date vencimiento;

	//bi-directional many-to-one association to Devolucion
	//@OneToMany(mappedBy="reclamo")
    @Transient
    private Set<Devolucion> devolucions;

	//bi-directional many-to-one association to Fideliza
	//@OneToMany(mappedBy="reclamo")
	@Transient
	private Set<Fideliza> fidelizas;

	//bi-directional many-to-one association to Indemnizacion
	//@OneToMany(mappedBy="reclamo")
	@Transient
	private Set<Indemnizacion> indemnizacions;

	//bi-directional many-to-one association to ItemsReclamo
	//@OneToMany(mappedBy="reclamo")
	@Transient
	private Set<ItemsReclamo> itemsReclamos;

	//bi-directional many-to-one association to Solucion
	//@OneToMany(mappedBy="reclamo")
	@Transient
	private Set<Solucion> solucions;

	//bi-directional many-to-one association to Factura
    @ManyToOne
	@JoinColumn(name="idFactura")
	private Factura factura;
    
    public Reclamo() {
    	this.estados = new ArrayList<Integer>();
    }

	public Long getIdReclamo() {
		return this.idReclamo;
	}

	public void setIdReclamo(Long idReclamo) {
		this.idReclamo = idReclamo;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
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
	/**
	 * 1=Abierto : cuando se registra, estado inicial<br>
	 * 2=Aceptado : cuando se evalua y es aceptado<br>
	 * 3=Rechazado : cuando se evalua y es rechazado<br>
	 * 4=solucionado: luego de que grabe la solucion en la pantalla de Solucion<br>
	 * Segun sus mockups: Abierto, en proceso, atendido
	 * @param estado
	 */
	public int getEstado() {
		return this.estado;
	}
	/**
	 * 1=Abierto : cuando se registra, estado inicial<br>
	 * 2=Aceptado : cuando se evalua y es aceptado<br>
	 * 3=Rechazado : cuando se evalua y es rechazado<br>
	 * 4=solucionado: luego de que grabe la solucion en la pantalla de Solucion<br>
	 * Segun sus mockups: Abierto, en proceso, atendido
	 * @param estado
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecReclamo() {
		return this.fecReclamo;
	}

	public void setFecReclamo(Date fecReclamo) {
		this.fecReclamo = fecReclamo;
	}

	public String getIndemnizar() {
		return this.indemnizar;
	}

	public void setIndemnizar(String indemnizar) {
		this.indemnizar = indemnizar;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getSolucion() {
		return this.solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public String getTipoReclamo() {
		return this.tipoReclamo;
	}

	public void setTipoReclamo(String tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getVencimiento() {
		return this.vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public Set<Devolucion> getDevolucions() {
		return this.devolucions;
	}

	public void setDevolucions(Set<Devolucion> devolucions) {
		this.devolucions = devolucions;
	}
	
	public Set<Fideliza> getFidelizas() {
		return this.fidelizas;
	}

	public void setFidelizas(Set<Fideliza> fidelizas) {
		this.fidelizas = fidelizas;
	}
	
	public Set<Indemnizacion> getIndemnizacions() {
		return this.indemnizacions;
	}

	public void setIndemnizacions(Set<Indemnizacion> indemnizacions) {
		this.indemnizacions = indemnizacions;
	}
	
	public Set<ItemsReclamo> getItemsReclamos() {
		return this.itemsReclamos;
	}

	public void setItemsReclamos(Set<ItemsReclamo> itemsReclamos) {
		this.itemsReclamos = itemsReclamos;
	}
	
	public Set<Solucion> getSolucions() {
		return this.solucions;
	}

	public void setSolucions(Set<Solucion> solucions) {
		this.solucions = solucions;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	 public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<Integer> getEstados() {
		return estados;
	}

	public void setEstados(List<Integer> estados) {
		this.estados = estados;
	}

	@Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}