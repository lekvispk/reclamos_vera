package pe.org.reclamos.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCliente;

	private int estado;

    @Temporal( TemporalType.TIMESTAMP)
	private Date fecCliente;

	private String nomCliente;

	private String representante;
	
	private String rucCliente;

	private Double monto;
	
	//bi-directional many-to-one association to Apelacion
	//@OneToMany(mappedBy="cliente")
	@Transient
	private Set<Apelacion> apelacions;

	//bi-directional many-to-one association to Persona
    @ManyToOne
	@JoinColumn(name="idPersona")
	private Persona persona;

	//bi-directional many-to-one association to Factura
	//@OneToMany(mappedBy="cliente")
    @Transient
	private Set<Factura> facturas;

	//bi-directional many-to-one association to Solicitud
	//@OneToMany(mappedBy="cliente")
    @Transient
	private Set<Solicitud> solicituds;

    public Cliente() {
    }

	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecCliente() {
		return this.fecCliente;
	}

	public void setFecCliente(Date fecCliente) {
		this.fecCliente = fecCliente;
	}

	public String getNomCliente() {
		return this.nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	public String getRucCliente() {
		return this.rucCliente;
	}

	public void setRucCliente(String rucCliente) {
		this.rucCliente = rucCliente;
	}

	public Set<Apelacion> getApelacions() {
		return this.apelacions;
	}

	public void setApelacions(Set<Apelacion> apelacions) {
		this.apelacions = apelacions;
	}
	
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public Set<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}
	
	public Set<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(Set<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}
	
	 public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", estado=" + estado + ", fecCliente=" + fecCliente + ", nomCliente="
				+ nomCliente + ", representante=" + representante + ", rucCliente=" + rucCliente + ", monto=" + monto
				+ "]";
	}

	
}