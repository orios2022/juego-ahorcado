package vista;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class CartelAdvertencia extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartelAdvertencia frame = new CartelAdvertencia();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CartelAdvertencia() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon icono = new ImageIcon("src/imagenes/icono.png");
		this.setIconImage(icono.getImage());
		
		JLabel lblLaPalabraIntroducida = new JLabel("La palabra introducida no cumple con algunas condiciones:\r\n");
		lblLaPalabraIntroducida.setForeground(Color.RED);
		lblLaPalabraIntroducida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLaPalabraIntroducida.setBounds(10, 11, 394, 27);
		contentPane.add(lblLaPalabraIntroducida);
		
		JLabel lblNoContieneLetras = new JLabel("* No contiene letras (campo vac\u00EDo)");
		lblNoContieneLetras.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNoContieneLetras.setBounds(20, 49, 227, 14);
		contentPane.add(lblNoContieneLetras);
		
		JLabel lblSuperEl = new JLabel("* Super\u00F3 el l\u00EDmite m\u00E1ximo de 10 letras");
		lblSuperEl.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSuperEl.setBounds(20, 74, 227, 14);
		contentPane.add(lblSuperEl);
		
		JButton btnRevisar = new JButton("REVISAR");
		btnRevisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnRevisar.setBackground(Color.RED);
		btnRevisar.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		btnRevisar.setForeground(Color.YELLOW);
		btnRevisar.setBounds(315, 89, 89, 23);
		contentPane.add(btnRevisar);
	}
}
