package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.Controlador;

public class IniciaPalabra<TextView> extends JFrame {

	private JLabel titulo;
	private JLabel subtitulo;
	private JTextField introducirPalabra;
	private String contenidoTitulo;
	private String contenidoSubTitulo;
	private JButton comenzar;
	private JButton score;
	private AcertarPalabra ap;

	public IniciaPalabra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
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
		
		fondo f = new fondo();
		f.setLayout(null);
		add(f);
		
		ImageIcon icono = new ImageIcon("src/imagenes/icono.png");
		this.setIconImage(icono.getImage());
		
		contenidoTitulo = "Ahorcado";
		titulo = new JLabel(contenidoTitulo);
		titulo.setFont(new Font("poxel font", 1, 60));
		titulo.setForeground(Color.white);
		titulo.setBounds(100, -50, 500, 200);
		f.add(titulo);
		
		 TimerTask timerTask = new TimerTask()
	     {
	         public void run() 
	         {
	        	 titulo.setForeground(Color.yellow);
	         }
	         
	     };
	     
	     TimerTask timerTask2 = new TimerTask()
	     {
	         public void run() 
	         {
	        	 titulo.setForeground(Color.white);
	         }
	         
	     };
	      // Aquí se pone en marcha el timer cada segundo.
	     Timer timer = new Timer(); 
	     // Dentro de 0 milisegundos avísame cada 1000 milisegundos
	     timer.scheduleAtFixedRate(timerTask, 1000, 900);
	     timer.scheduleAtFixedRate(timerTask2, 1000, 900);
	     
	     
		contenidoSubTitulo = "Introduce la palabra secreta";
		subtitulo = new JLabel(contenidoSubTitulo);
		subtitulo.setFont(new Font("poxel font", 1, 20));
		subtitulo.setForeground(Color.yellow);
		subtitulo.setBounds(85, 0, 500, 200);
		f.add(subtitulo);
		
		introducirPalabra = new JTextField();
		introducirPalabra.setBounds(85, 130, 320, 40);
		introducirPalabra.setFont(new Font("Verdana", 10, 26));
		introducirPalabra.setText(introducirPalabra.getText().toUpperCase());
		f.add(introducirPalabra);

		introducirPalabra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent k) {
				if(introducirPalabra.getText().length() >= 20)
			    {
			        k.consume();
			    }
			}
		});
		
		score = new JButton("PUNTAJE");
		score.setBounds(380, 400, 100, 50);		
		f.add(score);
		score.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Historial h = new Historial();
				h.setVisible(true);
				h.setResizable(false);
			}
			});
		
		comenzar = new JButton();
		comenzar.setBounds(190, 205, 100, 70);		
		ImageIcon play = new ImageIcon("src/imagenes/play.jpg");
		ImageIcon play2 = new ImageIcon(play.getImage().getScaledInstance(195, 120, java.awt.Image.SCALE_DEFAULT));
		comenzar.setIcon(play2);
		f.add(comenzar);
		
		
		comenzar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String palabraExtraida = introducirPalabra.getText().trim();

				if (!palabraExtraida.isEmpty()) {
					Controlador.getInstance().setPalabraSecreta(palabraExtraida);
					ArrayList<String> mascara = Controlador.getInstance().getMascara();
					int tamañoVentana = 550;
					
					switch(mascara.size()){
					case 8: tamañoVentana = 600;
						break;
					case 9: tamañoVentana = 650;
					break;
					case 10: tamañoVentana = 700;
					break;
					case 11: tamañoVentana = 750;
					break;
					case 12: tamañoVentana = 800;
					break;
					case 13: tamañoVentana = 850;
					break;
					case 14: tamañoVentana = 900;
					break;
					case 15: tamañoVentana = 950;
					break;
					case 16: tamañoVentana = 1000;
					break;
					case 17: tamañoVentana = 1050;
					break;
					case 18: tamañoVentana = 1100;
					break;
					case 19: tamañoVentana = 1150;
					break;
					case 20: tamañoVentana = 1200;
					break;
					}
					ap = new AcertarPalabra(tamañoVentana);
					ap.setVisible(true);
					ap.setBounds(400, 100, tamañoVentana, 400);
					dispose();

				} else{
					ImageIcon error = new ImageIcon(getClass().getResource("/imagenes/error.jpg"));
					Image image = error.getImage(); // transform it 
					Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_AREA_AVERAGING); 
					error = new ImageIcon(newimg);
					JOptionPane.showMessageDialog(null,"No puede estar vacía", "Ahorcado", JOptionPane.ERROR_MESSAGE, error);
				}
			}
			});
	}

	public class fondo extends JPanel{

		private Image fondo;
		private Image logo;
        @Override
        public void paint(Graphics g)
        {
        	fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.gif")).getImage();
            
            g.drawImage(fondo,0, 0,  getWidth(), getHeight(),this);                       
            setOpaque(false);	            
            super.paint(g);
            logo = new ImageIcon(getClass().getResource("/imagenes/pngwing.com.png")).getImage();
            
            g.drawImage(logo,100, 280,  290, 200,this);                       
            setOpaque(false);	            
            super.paint(g);

        }
	}
	


}
