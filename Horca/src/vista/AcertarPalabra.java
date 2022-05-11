package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Controlador;

public class AcertarPalabra extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int TAM = 50; 

	private JTextField txtLetraInsertada;
	private JTextField usuario;
	private JTextField[] letras;
	private Controlador control;
	private int intentos = 10;
	private int tamañoVentana = 0;
	private Clip acierto;
	private Clip winner;
	private JLabel lblPuntos;
	private boolean corroboracion;
	private ArrayList<String> palabra;
	private JButton salir;
	private JButton btnVerificar;
	private JButton confirmarUsuario;
	private String nombreUsuario;
	public AcertarPalabra(final int anchoFrame) {
		
		
		ImageIcon icono = new ImageIcon("src/imagenes/icono.png");
		this.setIconImage(icono.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		fondo f = new fondo();
		f.setLayout(null);
		add(f);

		control = Controlador.getInstance();
		final ArrayList<String> mascara = control.getMascara();
		
		switch(mascara.size()){
		case 7: tamañoVentana = 200;
			break;
		
		}
		
		f.setBounds(400, 100, tamañoVentana, 200);
		
		letras = new JTextField[mascara.size()];
		for(int i = 0; i < letras.length; i++) {
			letras[i] = new JTextField();
		}
		
		
		final JLabel lblIntentos = new JLabel("INTENTOS:");
		lblIntentos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIntentos.setBounds(10, 21, 250, 25);
		lblIntentos.setFont(new Font("poxel font", 250, 25));
		lblIntentos.setForeground(Color.white);
		f.add(lblIntentos);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/imagenes/poxel-font.ttf")));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lblPuntos = new JLabel(String.valueOf(intentos));
		lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntos.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPuntos.setForeground(Color.RED);
		lblPuntos.setBounds(150, 22, 154, 21);
		f.add(lblPuntos);
		
		lblPuntos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent k) {
				if(lblPuntos.getText().equalsIgnoreCase("0"))
			    {
					try {
						winner = AudioSystem.getClip();
						winner.open(AudioSystem.getAudioInputStream(new File("src/sonidos/perdedor.wav")));
						winner.start();
					} catch (LineUnavailableException ee) {
						System.out.println("" + ee);
						ee.printStackTrace();
					} catch (IOException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					} catch (UnsupportedAudioFileException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
			    }

			}
		});
		
		int posX = 130;
		for(JTextField letra : letras) {
			letra.setFont(new Font("Simplified Arabic Fixed", Font.BOLD, 20));
			letra.setHorizontalAlignment(JLabel.CENTER);
			letra.setHorizontalAlignment(JTextField.CENTER);
			letra.setBackground(Color.black);
			letra.setOpaque(true);
			letra.setBounds(posX, 80, TAM, TAM);
			letra.setEnabled(false);
			posX += TAM;
			f.add(letra);
		}
		
		txtLetraInsertada = new JTextField();
		txtLetraInsertada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent k) {
				if(txtLetraInsertada.getText().length() >= 1)
			    {
			        k.consume();
			    }
				else{
					txtLetraInsertada.getText().toUpperCase();
				}
			}
		});
		txtLetraInsertada.setBackground(SystemColor.menu);
		txtLetraInsertada.setHorizontalAlignment(SwingConstants.CENTER);
		txtLetraInsertada.setFont(new Font("Tahoma", Font.BOLD, 55));
		txtLetraInsertada.setBounds(10, 191, 86, 60);
		txtLetraInsertada.setEnabled(false);
		f.add(txtLetraInsertada);
		txtLetraInsertada.setColumns(10);
		
		usuario = new JTextField();
		usuario.setBackground(SystemColor.menu);
		usuario.setHorizontalAlignment(SwingConstants.CENTER);
		usuario.setFont(new Font("Tahoma", Font.BOLD, 25));
		usuario.setBounds(10, 300, 200, 30);
		f.add(usuario);
		usuario.setColumns(10);
		
		usuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent k) {
				if(usuario.getText().length() >= 10)
			    {
			        k.consume();
			    }
			}
		});	
		
		JLabel lblInserteLetra = new JLabel("INSERTE");
		lblInserteLetra.setHorizontalAlignment(SwingConstants.CENTER);
		lblInserteLetra.setForeground(Color.BLUE);
		lblInserteLetra.setFont(new Font("Simplified Arabic Fixed", Font.BOLD, 15));
		lblInserteLetra.setBounds(10, 145, 86, 22);
		f.add(lblInserteLetra);
		
		JLabel lblLetra = new JLabel("LETRA");
		lblLetra.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetra.setForeground(Color.BLUE);
		lblLetra.setFont(new Font("Simplified Arabic Fixed", Font.BOLD, 15));
		lblLetra.setBounds(10, 166, 86, 22);
		f.add(lblLetra);	
		
		JLabel nomUsuario = new JLabel("NOMBRE DE USUARIO");
		nomUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		nomUsuario.setForeground(Color.RED);
		nomUsuario.setFont(new Font("Simplified Arabic Fixed", Font.BOLD, 15));
		nomUsuario.setBounds(10, 230, 200, 100);
		f.add(nomUsuario);	
		
		confirmarUsuario = new JButton("CONFIRMAR");
		confirmarUsuario.setFont(new Font("Verdana", Font.BOLD, 12));
		confirmarUsuario.setBounds(250, 300, 130, 30);
		confirmarUsuario.setEnabled(true);
		f.add(confirmarUsuario);
		
		confirmarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtLetraInsertada.setEnabled(true);
				btnVerificar.setEnabled(true);
				nombreUsuario = usuario.getText();
				confirmarUsuario.setEnabled(false);
				usuario.setEnabled(false);
				setLocation(170, 200);
				setBounds(350, 150, anchoFrame, 300);
			}
		});
		
		salir = new JButton("SALIR");
		salir.setFont(new Font("Verdana", Font.BOLD, 12));
		salir.setBounds(425, 203, 90, 25);
		salir.setEnabled(false);
		f.add(salir);
		
		salir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			System.exit(0);
				
		}
	});

		btnVerificar = new JButton("VERIFICAR");
		btnVerificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerificar.setBounds(114, 203, 102, 35);
		btnVerificar.setEnabled(false);
		btnVerificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = txtLetraInsertada.getText().trim();
				if(!s.isEmpty()) {
					procesarIntento(s);
					ganador(intentos);
					
				}else{
					
				}
				
			}
		});
		f.add(btnVerificar);

	}
	
	
	
	public void procesarIntento(String letra) {
		
		txtLetraInsertada.setText("");
		palabra = control.getMascara();
		corroboracion = control.getMascara().contains(letra);
		
		if(corroboracion) {
		for(int i = 0; i<palabra.size(); i++){
			if(letra.equalsIgnoreCase(palabra.get(i))){
				letras[i].setText(letra.toUpperCase());
				
				sonidoAcierto();
			}
		}
		}
			else{
			sonidoError();
			intentos = intentos - 1;
			lblPuntos.setText(String.valueOf(intentos));
		}
	}
	
	public void ganador(int i){
		if(i == 0){
			sonidoPerdedor();
			lblPuntos.setText("PERDISTE");
			txtLetraInsertada.setEnabled(false);
			btnVerificar.setEnabled(false);
			salir.setEnabled(true);
			BBDD();
			for(int j = 0;j<letras.length;j++){
				if(letras[j].getText().equals("")){
					
					letras[j].setText(palabra.get(j).toUpperCase());
					letras[j].setBackground(Color.gray);
				}
			}
		}
		else
		if(contadorGanador()==1){
			sonidoGanador();
			txtLetraInsertada.setEnabled(false);
			btnVerificar.setEnabled(false);
			salir.setEnabled(true);
			BBDD();
		}
		
	}
	
	private int contadorGanador(){
		int signal = 1;
		for(int i = 0;i<letras.length;i++){
			if(letras[i].getText().isEmpty()){
				signal = 0;
			}
		}
		
		return signal;
		
	}
	
	public void sonidoAcierto(){
		try {
			acierto = AudioSystem.getClip();
			acierto.open(AudioSystem.getAudioInputStream(new File("src/sonidos/acierto.wav")));
			acierto.start();
		} catch (LineUnavailableException e) {
			System.out.println("" + e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sonidoError(){
		try {
			acierto = AudioSystem.getClip();
			acierto.open(AudioSystem.getAudioInputStream(new File("src/sonidos/error.wav")));
			acierto.start();
		} catch (LineUnavailableException e) {
			System.out.println("" + e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sonidoGanador() {
		try {
			winner = AudioSystem.getClip();
			winner.open(AudioSystem.getAudioInputStream(new File("src/sonidos/ganador.wav")));
			winner.start();
		} catch (LineUnavailableException ee) {
			System.out.println("" + ee);
			ee.printStackTrace();
		} catch (IOException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		} catch (UnsupportedAudioFileException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
	}
	
	private void sonidoPerdedor() {
		try {
			winner = AudioSystem.getClip();
			winner.open(AudioSystem.getAudioInputStream(new File("src/sonidos/perdedor.wav")));
			winner.start();
		} catch (LineUnavailableException ee) {
			System.out.println("" + ee);
			ee.printStackTrace();
		} catch (IOException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		} catch (UnsupportedAudioFileException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
	}

	public class fondo extends JPanel {

		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private Image fondo;

		@Override
		public void paint(Graphics g) {
			fondo = new ImageIcon(getClass().getResource(
					"/imagenes/fondo.gif")).getImage();

			g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);

		}

	}
	
	public void BBDD() {
	Controlador.getInstance().setUsuario(nombreUsuario);
	Controlador.getInstance().setIntentos(intentos);
	Controlador.getInstance().setFecha(getFecha());
	Controlador.getInstance().ConfirmarRegistro();
	}
	
	public String getFecha() {
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		return currentTime;
	}
}