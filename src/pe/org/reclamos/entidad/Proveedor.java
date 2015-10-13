package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_proveedor database table.
 * 
 */
@Entity
@Table(name="tb_proveedor")
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProveedor;

	private int estado;

	private String razonSocial;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="tbProveedor")
	private List<Factura> tbFacturas;

	public Proveedor() {
	}

	public int getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public List<Factura> getTbFacturas() {
		return this.tbFacturas;
	}

	public void setTbFacturas(List<Factura> tbFacturas) {
		this.tbFacturas = tbFacturas;
	}

	public Factura addTbFactura(Factura tbFactura) {
		getTbFacturas().add(tbFactura);
		tbFactura.setTbProveedor(this);

		return tbFactura;
	}

	public Factura removeTbFactura(Factura tbFactura) {
		getTbFacturas().remove(tbFactura);
		tbFactura.setTbProveedor(null);

		return tbFactura;
	}

}