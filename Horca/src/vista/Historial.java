package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controlador.Controlador;

public class Historial extends JFrame{

	private JLabel titulo;
	private String contenidoTitulo;
	private JLabel usuario;
	private JLabel palabra;
	private JLabel intentos;
	private JLabel fecha;
	private JLabel datos;
	private int fila = 7;
	private JTextArea area;
	private JScrollPane scroll;
	private JButton reset;
	
	public Historial() {

		fondo2 f = new fondo2();
		f.setLayout(null);
		add(f);
		ImageIcon icono = new ImageIcon("src/imagenes/icono.png");
		this.setIconImage(icono.getImage());
		setBounds(400, 100, 500, 400);
		
		contenidoTitulo = "PUNTAJES";
		titulo = new JLabel(contenidoTitulo);
		titulo.setFont(new Font("poxel font", 1, 40));
		titulo.setForeground(Color.YELLOW);
		titulo.setBounds(120, -50, 500, 200);
		f.add(titulo);
		
		area = new JTextArea ();
		area.setEnabled(true);
		area.setBounds(30, 130, 410, 240);
		area.setBackground(Color.black);

		
		scroll = new JScrollPane (area);
		scroll.setBounds(30, 130, 410, 240);
		f.add(scroll);

		ResultSet rs = null;
		
		try {
			
			rs = Controlador.getInstance().ConfirmarConsulta();
			
			while(rs.next()){
				String user = rs.getString(1);				
				datos = new JLabel(user);					
				datos.setFont(new Font("Verdana", 1, 10));
				datos.setForeground(Color.YELLOW);
				datos.setBounds(15, fila, 70, 20);
				area.add(datos);
				
				String word = rs.getString(2);
				datos = new JLabel(word);
				datos.setFont(new Font("Verdana", 1, 10));
				datos.setForeground(Color.YELLOW);
				datos.setBounds(110, fila, 70, 20);
				area.add(datos);
				
				int trying = rs.getInt(3);
				datos = new JLabel(String.valueOf(trying));
				datos.setFont(new Font("Verdana", 1, 12));
				datos.setForeground(Color.YELLOW);
				datos.setBounds(260, fila, 20, 20);
				area.add(datos);
				
				String date = rs.getString(4);
				datos = new JLabel(date);
				datos.setFont(new Font("Verdana", 1, 10));
				datos.setForeground(Color.YELLOW);
				datos.setBounds(340, fila, 70, 20);
				area.add(datos);
				
				fila = fila+30;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		usuario = new JLabel("Usuario");
		usuario.setHorizontalAlignment(SwingConstants.CENTER);
		usuario.setFont(new Font("Tahoma", Font.BOLD, 25));
		usuario.setForeground(Color.RED);
		usuario.setBounds(0, 100, 154, 21);
		f.add(usuario);
		
		palabra = new JLabel("Palabra");
		palabra.setHorizontalAlignment(SwingConstants.CENTER);
		palabra.setFont(new Font("Tahoma", Font.BOLD, 25));
		palabra.setForeground(Color.RED);
		palabra.setBounds(110, 100, 154, 21);
		f.add(palabra);
		
		intentos = new JLabel("Errores");
		intentos.setHorizontalAlignment(SwingConstants.CENTER);
		intentos.setFont(new Font("Tahoma", Font.BOLD, 25));
		intentos.setForeground(Color.RED);
		intentos.setBounds(220, 100, 154, 21);
		f.add(intentos);
		
		fecha = new JLabel("Fecha");
		fecha.setHorizontalAlignment(SwingConstants.CENTER);
		fecha.setFont(new Font("Tahoma", Font.BOLD, 25));
		fecha.setForeground(Color.RED);
		fecha.setBounds(330, 100, 154, 21);
		f.add(fecha);
		
		reset = new JButton("reset");
		reset.setBounds(400, 40, 80, 30);
		reset.setBackground(Color.red);
		f.add(reset);
		
		reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				Controlador.getInstance().ConfirmarEliminar();
				area.removeAll();
			}
			});
	}
	
	public class fondo2 extends JPanel{

		private Image fondo2;

        @Override
        public void paint(Graphics g)
        {
        	fondo2 = new ImageIcon(getClass().getResource("/imagenes/fondo.gif")).getImage();
            
            g.drawImage(fondo2,0, 0,  getWidth(), getHeight(),this);                       
            setOpaque(false);	            
            super.paint(g);

        }
	}
}
