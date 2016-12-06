package pe.org.reclamos.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the despachador database table.
 * 
 */
@Entity
@Table(name="despachador")
public class Despachador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDespachador;

	private String apellidos;

	private String codigo;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private int estado;

	private String nombres;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to DetalleDevolucion
	@OneToMany(mappedBy="despachador")
	private Set<DetalleDevolucion> detalleDevolucions;

    public Despachador() {
    }

	public int getIdDespachador() {
		return this.idDespachador;
	}

	public void setIdDespachador(int idDespachador) {
		this.idDespachador = idDespachador;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
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
	
}