package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * The persistent class for the items_reclamo database table.
 * 
 */
@Entity
@Table(name="items_reclamo")
public class ItemsReclamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idItemReclamo;

	private int estado;

	//bi-directional many-to-one association to Reclamo
    @ManyToOne
	@JoinColumn(name="idReclamo")
	private Reclamo reclamo;

	//bi-directional many-to-one association to Detallefactura
    @ManyToOne
	@JoinColumn(name="idDetalleFactura")
	private Detallefactura detallefactura;

    public ItemsReclamo() {
    }

	public int getIdItemReclamo() {
		return this.idItemReclamo;
	}

	public void setIdItemReclamo(int idItemReclamo) {
		this.idItemReclamo = idItemReclamo;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Reclamo getReclamo() {
		return this.reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}
	
	public Detallefactura getDetallefactura() {
		return this.detallefactura;
	}

	public void setDetallefactura(Detallefactura detallefactura) {
		this.detallefactura = detallefactura;
	}

	 @Override
     public String toString() {
          return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
     }
	 
}