package main;

import javax.swing.JFrame;

import vista.IniciaPalabra;

public class Inicio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IniciaPalabra ip = new IniciaPalabra();
		ip.setVisible(true);	
		ip.setBounds(400,100,500,500);
		ip.setResizable(false);
		ip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.getProperty("java.classpath");
	}

}
