package controlador;

import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Modelo;

public class Controlador {

	private static Controlador controlador = null;
	
	private Controlador() {	}
	
	public static Controlador getInstance() {
		if(controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}

	public void setPalabraSecreta(String palabraSecreta) {
		Modelo.getInstance().setPalabraSecreta(palabraSecreta);
	}
	
	public ArrayList<String> getMascara() {
		return Modelo.getInstance().getMascara();
	}
	
	public String getPalabraSecreta(){
		return Modelo.getInstance().getPalabraSecreta();
	}
	
	public void setUsuario(String usuario) {
		Modelo.getInstance().setUsuario(usuario);
	}

	public void setIntentos(int intentos) {
		Modelo.getInstance().setIntentos(intentos);
	}
	
	public void setFecha(String fecha) {
		Modelo.getInstance().setFecha(fecha);
	}
	
	public void ConfirmarConexion() {
		Modelo.getInstance().ConfirmarConexion();
	}

	public void ConfirmarRegistro() {
		Modelo.getInstance().ConfirmarRegistro();	
	}
	
	public ResultSet ConfirmarConsulta() {
		return Modelo.getInstance().ConfirmarConsulta();	
	}
	
	public void ConfirmarEliminar() {
		Modelo.getInstance().ConfirmarEliminar();	
	}
}

