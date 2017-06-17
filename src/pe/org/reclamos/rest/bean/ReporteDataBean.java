package pe.org.reclamos.rest.bean;

import java.io.Serializable;

public class ReporteDataBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String label;
	private String value;
	
	public ReporteDataBean(){
		super();
	}
	
	public ReporteDataBean(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
