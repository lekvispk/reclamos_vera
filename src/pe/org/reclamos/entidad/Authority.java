package pe.org.reclamos.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the authorities database table.
 * 
 */
@Entity
@Table(name="authorities")
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String authority;

	private String username;

    public Authority() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}