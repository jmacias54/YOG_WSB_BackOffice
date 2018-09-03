package mx.com.amx.wsb.yog.backoffice.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the yog_n_eventos database table.
 * 
 */

public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idEvento;

	private String fcCeremonia;
	private String fcIdDeporte;
	private String fcNombreEvento;

	private String fcUrlHighligths;

	private String fcCanalLive;

	private String fcUrlVod;

	private Timestamp fdFechaActualizacion;

	private Timestamp fdFechaCreacion;

	private String fdFechaFin;

	private String fdFechaIni;

	public Evento() {
	}

	public String getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public String getFcCeremonia() {
		return this.fcCeremonia;
	}

	public void setFcCeremonia(String fcCeremonia) {
		this.fcCeremonia = fcCeremonia;
	}

	public String getFcNombreEvento() {
		return this.fcNombreEvento;
	}

	public void setFcNombreEvento(String fcNombreEvento) {
		this.fcNombreEvento = fcNombreEvento;
	}

	public String getFcUrlHighligths() {
		return this.fcUrlHighligths;
	}

	public void setFcUrlHighligths(String fcUrlHighligths) {
		this.fcUrlHighligths = fcUrlHighligths;
	}

	

	public String getFcCanalLive() {
		return fcCanalLive;
	}

	public void setFcCanalLive(String fcCanalLive) {
		this.fcCanalLive = fcCanalLive;
	}

	public String getFcUrlVod() {
		return this.fcUrlVod;
	}

	public void setFcUrlVod(String fcUrlVod) {
		this.fcUrlVod = fcUrlVod;
	}

	public Timestamp getFdFechaActualizacion() {
		return this.fdFechaActualizacion;
	}

	public void setFdFechaActualizacion(Timestamp fdFechaActualizacion) {
		this.fdFechaActualizacion = fdFechaActualizacion;
	}

	public Timestamp getFdFechaCreacion() {
		return this.fdFechaCreacion;
	}

	public void setFdFechaCreacion(Timestamp fdFechaCreacion) {
		this.fdFechaCreacion = fdFechaCreacion;
	}

	public String getFdFechaFin() {
		return this.fdFechaFin;
	}

	public void setFdFechaFin(String fdFechaFin) {
		this.fdFechaFin = fdFechaFin;
	}

	public String getFdFechaIni() {
		return this.fdFechaIni;
	}

	public void setFdFechaIni(String fdFechaIni) {
		this.fdFechaIni = fdFechaIni;
	}

	public String getFcIdDeporte() {
		return fcIdDeporte;
	}

	public void setFcIdDeporte(String fcIdDeporte) {
		this.fcIdDeporte = fcIdDeporte;
	}

	@Override
	public String toString() {
		return "Evento [idEvento=" + idEvento + ", fcCeremonia=" + fcCeremonia + ", fcIdDeporte=" + fcIdDeporte
				+ ", fcNombreEvento=" + fcNombreEvento + ", fcUrlHighligths=" + fcUrlHighligths + ", fcCanalLive="
				+ fcCanalLive + ", fcUrlVod=" + fcUrlVod + ", fdFechaActualizacion=" + fdFechaActualizacion
				+ ", fdFechaCreacion=" + fdFechaCreacion + ", fdFechaFin=" + fdFechaFin + ", fdFechaIni=" + fdFechaIni
				+ "]";
	}

	

}