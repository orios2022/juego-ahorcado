package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Modelo {

	private static ArrayList<String> mascara;
	private String palabra;
	private Connection miConexion;
	private String usuario;
	private int intentos;
	private String fecha;

	private static Modelo modelo = null;

	private Connection Conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.toString(), "Proyecto", 0);
		}
		
		// BASE DE DATOS

		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ahorcado","root", "");
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return miConexion;
	}
	
	public void ConexionRegistro () {
		
		// INSERTAR REGISTROS DE RESULTADOS
		try {
			CallableStatement cs = Conexion().prepareCall("{call INSERTAR_AHORCADO (?,?,?,?)}");
			
			cs.setString(1, getUsuario());
			cs.setString(2, getPalabraSecreta());
			cs.setInt(3, getIntentos());
			cs.setString(4, getFecha());
			cs.executeUpdate();
			System.out.print("registro ingresado");
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.print("error de registro");		}
	}
	
	public ResultSet ConexionConsulta(){
		// CONSULTAR RESULTADOS
		// INSERTAR REGISTROS DE RESULTADOS
		ResultSet rs = null;
				try {
					CallableStatement cs = Conexion().prepareCall("{call CONSULTAR_AHORCADO ()}");
					rs = cs.executeQuery();
					System.out.print("consulta ingresada");
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.print("error de consulta");
					e1.printStackTrace();		}
				return rs;
	}
	
	public void ConexionEliminar(){
		// ELIMINAR RESULTADOS

				try {
					CallableStatement cs = Conexion().prepareCall("{call ELIMINAR_AHORCADO ()}");
					cs.executeUpdate();
					System.out.print("eliminacion ingresada");
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.print("error de eliminacion");
					e1.printStackTrace();		}
	}
	
	public static Modelo getInstance() {
		if (modelo == null) {
			mascara = new ArrayList();
			modelo = new Modelo();
		}
		return modelo;
	}

	public void setPalabraSecreta(String palabraSecreta) {
		palabra = palabraSecreta;
		for(int i = 0; i < palabraSecreta.length(); i++) {
			mascara.add(palabraSecreta.substring(i, i+1));			
			}
	}
	
	public String getPalabraSecreta(){
		return palabra;
		
	}
	
	public ArrayList<String> getMascara() {
		return mascara ;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
		
	}
	
	public String getUsuario() {
		return usuario;
		
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;	
	}
	
	public int getIntentos() {
		return intentos;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;	
	}

	public String getFecha() {
		return fecha;	
	}
	
	public void ConfirmarConexion() {
		Conexion();
	}
	
	public void ConfirmarRegistro() {
		ConfirmarConexion();
		ConexionRegistro();
	}
	
	public ResultSet ConfirmarConsulta() {
		ConfirmarConexion();
		return ConexionConsulta();
	}
	
	public void ConfirmarEliminar() {
		ConexionEliminar();
	}
}