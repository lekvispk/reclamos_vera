package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;


/**
 * The persistent class for the parametro database table.
 * 
 */
@Entity
@Table(name="parametro")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idParametro;

	@Column(name="cod_grupo")
	private String codGrupo;

	@Column(name="cod_param")
	private String codParam;

	@Column(name="des_corta")
	private String desCorta;

	@Column(name="des_param")
	private String desParam;

	private int estado;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="fec_modif")
	private Date fecModif;

    public Parametro() {
    }

	public int getIdParametro() {
		return this.idParametro;
	}

	public void setIdParametro(int idParametro) {
		this.idParametro = idParametro;
	}

	public String getCodGrupo() {
		return this.codGrupo;
	}

	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}

	public String getCodParam() {
		return this.codParam;
	}

	public void setCodParam(String codParam) {
		this.codParam = codParam;
	}

	public String getDesCorta() {
		return this.desCorta;
	}

	public void setDesCorta(String desCorta) {
		this.desCorta = desCorta;
	}

	public String getDesParam() {
		return this.desParam;
	}

	public void setDesParam(String desParam) {
		this.desParam = desParam;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecModif() {
		return this.fecModif;
	}

	public void setFecModif(Date fecModif) {
		this.fecModif = fecModif;
	}

	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}