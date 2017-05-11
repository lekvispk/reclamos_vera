package pe.org.reclamos.rest.bean;

import java.io.Serializable;

public class NotificacionRest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String razonSocial;
	private String representante;
	private String fechaReclamo;
	private String asuntoReclamo;
	private String respuestaReclamo;
	
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getFechaReclamo() {
		return fechaReclamo;
	}
	public void setFechaReclamo(String fechaReclamo) {
		this.fechaReclamo = fechaReclamo;
	}
	public String getAsuntoReclamo() {
		return asuntoReclamo;
	}
	public void setAsuntoReclamo(String asuntoReclamo) {
		this.asuntoReclamo = asuntoReclamo;
	}
	public String getRespuestaReclamo() {
		return respuestaReclamo;
	}
	public void setRespuestaReclamo(String respuestaReclamo) {
		this.respuestaReclamo = respuestaReclamo;
	}
	
}
