package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class CartelAdvertencia2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CartelAdvertencia2() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 161);
		ImageIcon icono = new ImageIcon("src/imagenes/icono.png");
		this.setIconImage(icono.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLaPalabraIntroducida = new JLabel("La letra introducida no cumple con algunas condiciones:\r\n");
		lblLaPalabraIntroducida.setForeground(Color.RED);
		lblLaPalabraIntroducida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLaPalabraIntroducida.setBounds(10, 11, 394, 27);
		contentPane.add(lblLaPalabraIntroducida);
		
		JLabel lblNoContieneLetras = new JLabel("* Campo vac\u00EDo");
		lblNoContieneLetras.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNoContieneLetras.setBounds(20, 49, 227, 14);
		contentPane.add(lblNoContieneLetras);
		
		JLabel lblSuperEl = new JLabel("* Solo se permite un caracter a la vez");
		lblSuperEl.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSuperEl.setBounds(20, 74, 312, 14);
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
		
		JLabel lblNoDeje = new JLabel("* No deje espacios en blanco");
		lblNoDeje.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNoDeje.setBounds(20, 99, 227, 14);
		contentPane.add(lblNoDeje);
	}
}

