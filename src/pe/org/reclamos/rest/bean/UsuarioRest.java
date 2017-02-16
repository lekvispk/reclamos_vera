package pe.org.reclamos.rest.bean;

import java.io.Serializable;
import java.util.Date;

import pe.org.reclamos.entidad.Persona;

public class UsuarioRest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	private String password;
    private Date createdAt;
    private Date deletedAt;
	private String email;
	private int estado;	
	private int resetPassword;
    private Date passwordCaduca;
    private Date updatedAt;
	private String username;
	private Persona persona;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(int resetPassword) {
		this.resetPassword = resetPassword;
	}
	public Date getPasswordCaduca() {
		return passwordCaduca;
	}
	public void setPasswordCaduca(Date passwordCaduca) {
		this.passwordCaduca = passwordCaduca;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
