import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import org.apache.commons.lang3.text.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.Normalizer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

public class panel2 extends JPanel {
	public JTextField textNombres;
	public JTextField textApellidos;
	public JTextField txtDNI;
	public JTextField textUsuario;
	public JTextField textMail;
	public  static String nombres, apellidos, usuario, email;
	
	public static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }
	

	/**
	 * Create the panel.
	 */
	public panel2() {
		setLayout(null);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(10, 11, 74, 14);
		add(lblNombres);
		
		textNombres = new JTextField();
		textNombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				nombres = WordUtils.capitalizeFully(textNombres.getText()) ;
			}
		});
		textNombres.setBounds(108, 8, 318, 20);
		add(textNombres);
		textNombres.setColumns(10);
		

		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 35, 74, 14);
		add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				apellidos = WordUtils.capitalizeFully(textApellidos.getText()) ;
			}
		});
		textApellidos.setBounds(108, 39, 318, 20);
		add(textApellidos);
		textApellidos.setColumns(10);
		
		JLabel lblDni2 = new JLabel("DNI");
		lblDni2.setBounds(10, 69, 46, 14);
		add(lblDni2);
		
		txtDNI = new JTextField();
		txtDNI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		txtDNI.setBounds(108, 66, 86, 20);
		add(txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblAlerta = new JLabel("");
		lblAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlerta.setBounds(162, 160, 264, 20);
		add(lblAlerta);
		
		
		JRadioButton rdbtnEmpleado = new JRadioButton("Empleado");
		rdbtnEmpleado.setBounds(10, 108, 92, 23);
		add(rdbtnEmpleado);
		
		JRadioButton rdbtnContratado = new JRadioButton("Contratado");
		rdbtnContratado.setBounds(114, 108, 109, 23);
		add(rdbtnContratado);
		
		JRadioButton rdbtnExterno = new JRadioButton("Externo");
		rdbtnExterno.setBounds(218, 108, 109, 23);
		add(rdbtnExterno);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 163, 46, 14);
		add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Buscar ldap = new Buscar();
				String user = textUsuario.getText();
				Buscar resultado = new Buscar();
				if(textUsuario.getText().length()>3) {
				resultado = ldap.buscarUsuario(user);
				if( resultado.error == true) {
					lblAlerta.setForeground(Color.red);
					lblAlerta.setText("El usuario ya existe en AD");
					
				}
				else {
					lblAlerta.setForeground(Color.blue);
					lblAlerta.setText("Usuario OK");
				}
			}
				usuario =panel2.cleanString(textUsuario.getText().toLowerCase());
			}
		});
		textUsuario.setBounds(66, 160, 86, 20);
		add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(10, 199, 46, 14);
		add(lblMail);
		
		textMail = new JTextField();
		textMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				email = panel2.cleanString(textMail.getText().toLowerCase());
			}
		});
		textMail.setBounds(66, 196, 231, 20);
		add(textMail);
		textMail.setColumns(10);
		
		
		
		
	
		
		
		

	}
}
