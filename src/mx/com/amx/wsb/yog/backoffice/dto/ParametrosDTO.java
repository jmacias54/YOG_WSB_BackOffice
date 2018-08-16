package mx.com.amx.wsb.yog.backoffice.dto;

import java.io.Serializable;

public class ParametrosDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dominio;
	private String ambiente;
	private String url;

	private String backofficeController;

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBackofficeController() {
		return backofficeController;
	}

	public void setBackofficeController(String backofficeController) {
		this.backofficeController = backofficeController;
	}

	@Override
	public String toString() {
		return "ParametrosDTO [dominio=" + dominio + ", ambiente=" + ambiente + ", url=" + url
				+ ", backofficeController=" + backofficeController + "]";
	}

}
