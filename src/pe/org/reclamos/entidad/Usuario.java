package pe.org.reclamos.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
public class Usuario  extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idUsuario;

	@Column(name="password")
	private String password;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="deleted_at")
	private Date deletedAt;

	private String email;

	private int estado;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="password_caduca")
	private Date passwordCaduca;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	private String username;

	//bi-directional many-to-one association to Persona
    @ManyToOne
	@JoinColumn(name="idPersona")
	private Persona persona;

	//bi-directional many-to-one association to Perfil
    @ManyToOne
	@JoinColumn(name="idPerfil")
	private Perfil perfil;

    public Usuario() {
    	super("default", "default", true, true, true, true , uno() );		
    }
	
	public static List<GrantedAuthority> uno(){
		List<GrantedAuthority> oo = new ArrayList<GrantedAuthority>(); 
		oo.add(new GrantedAuthorityImpl("IS_AUTHENTICATED_ANONYMOUSLY") );
		return oo;
	}
	
    public static Usuario getUsuarioBean() {
    	Usuario nu = (Usuario)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if(nu != null) {
			return nu;
		}
		else return null;
	}
	
    public Usuario(String username, String password, boolean enabled,Collection<GrantedAuthority> authorities) {
		super(username, password, enabled, true, true, true, authorities);
		this.estado = enabled==true ? 1 : 0;
		this.username = username;
		this.password = password;
	}


	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password ) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeletedAt() {
		return this.deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getPasswordCaduca() {
		return this.passwordCaduca;
	}

	public void setPasswordCaduca(Date passwordCaduca) {
		this.passwordCaduca = passwordCaduca;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}