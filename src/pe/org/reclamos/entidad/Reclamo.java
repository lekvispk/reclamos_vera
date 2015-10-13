package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_reclamos database table.
 * 
 */
@Entity
@Table(name="tb_reclamos")
@NamedQuery(name="Reclamo.findAll", query="SELECT r FROM Reclamo r")
public class Reclamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idReclamo;

	private String asunto;

	private String descripcion;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecReclamo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecVencimiento;
	  
	private String mensaje;

	private int prioridad;

	private String solucion;
	
	private String tipoReclamo;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumn(name="idFactura")
	private Factura factura;

	public Reclamo() {
		factura = new Factura();
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

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 1=Abierto<br>
	 * 2=Aceptado<br>
	 * 3=Rechazado<br>
	 * 4=solucionado<br>
	 * @return
	 */
	public int getEstado() {
		return this.estado;
	}

	/**
	 * 1=Abierto<br>
	 * 2=Aceptado<br>
	 * 3=Rechazado<br>
	 * 4=solucionado<br>
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

	public String getTipoReclamo() {
		return this.tipoReclamo;
	}

	public void setTipoReclamo(String tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura tbFactura) {
		this.factura = tbFactura;
	}

	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public Date getFecRegistro() {
		return fecRegistro;
	}

	public void setFecRegistro(Date fecRegistro) {
		this.fecRegistro = fecRegistro;
	}

	public Date getFecVencimiento() {
		return fecVencimiento;
	}

	public void setFecVencimiento(Date fecVencimiento) {
		this.fecVencimiento = fecVencimiento;
	}

	@Override
	public String toString(){
		return "id: " + idReclamo + " asunto: "+ asunto;
	}
	
}