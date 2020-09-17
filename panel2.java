import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import org.apache.commons.lang3.text.*;
import java.text.Normalizer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panel2 extends JPanel {
	public JTextField textNombres;
	public JTextField textApellidos;
	public JTextField txtDNI;
	public JTextField textUsuario;
	public JTextField textMail;
	public  static String nombres, apellidos, usuario, email, password, tipoEmpleado, legajo;
	ClasesPanel2 cp2 = new ClasesPanel2();
	private JTextField textLegajo;

	
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
			@SuppressWarnings("deprecation")
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
			@SuppressWarnings("deprecation")
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
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(10, 240, 46, 14);
		add(lblMail);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(180, 160, 216, 20);
		add(lblNewLabel);
		
		JLabel lblpwText = new JLabel("Password");
		lblpwText.setBounds(10, 199, 62, 14);
		add(lblpwText);
		
		JLabel lblPw = new JLabel("");
		lblPw.setBounds(84, 199, 86, 14);
		add(lblPw);
		
		JLabel lblLegajo = new JLabel("Legajo");
		lblLegajo.setBounds(10, 275, 62, 14);
		add(lblLegajo);
		lblLegajo.setVisible(false);
		
		textLegajo = new JTextField();
		textLegajo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				legajo=textLegajo.getText();
			}
		});
		textLegajo.setBounds(84, 272, 114, 20);
		textLegajo.setColumns(10);
		add(textLegajo);	
		textLegajo.setVisible(false);
		
		JRadioButton rdbtnEmpleado = new JRadioButton("Empleado");
		rdbtnEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tipoEmpleado = "E";
				lblLegajo.setVisible(true);
				textLegajo.setVisible(true);				
				textMail.setVisible(false);
				lblMail.setVisible(false);
				textUsuario.setText(panel2.cleanString(cp2.CrearUsuario(nombres, apellidos)));
				password = panel2.cleanString(cp2.CrearPass(nombres, apellidos));
				//password = cp2.CrearPass(nombres, apellidos);
				lblPw.setText(password);
				Buscar ldap = new Buscar();
				String user = textUsuario.getText();
				Buscar resultado = new Buscar();
				resultado = ldap.buscarUsuario(user);
				if( resultado.error == true) {
					lblNewLabel.setForeground(Color.red);
					lblNewLabel.setText("El usuario ya existe en AD");
					
				}
				else {
					lblNewLabel.setForeground(Color.blue);
					lblNewLabel.setText("Usuario OK");
				}
				usuario =panel2.cleanString(textUsuario.getText().toLowerCase());
			}
		});
		rdbtnEmpleado.setBounds(10, 108, 92, 23);
		add(rdbtnEmpleado);
		
		JRadioButton rdbtnContratado = new JRadioButton("Contratado");
		rdbtnContratado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoEmpleado = "C";
				 textMail.setVisible(true);
				 lblMail.setVisible(true);
				 textUsuario.setText(panel2.cleanString(cp2.CrearUsuario(nombres, apellidos)));
					password = panel2.cleanString(cp2.CrearPass(nombres, apellidos));
					lblPw.setText(password);
				 Buscar ldap = new Buscar();
				 String user = textUsuario.getText();
					Buscar resultado = new Buscar();
					resultado = ldap.buscarUsuario(user);
					if( resultado.error == true) {
						lblNewLabel.setForeground(Color.red);
						lblNewLabel.setText("El usuario ya existe en AD");
						
					}
					else {
						lblNewLabel.setForeground(Color.blue);
						lblNewLabel.setText("Usuario OK");
					}
					usuario =panel2.cleanString(textUsuario.getText().toLowerCase());
			}
		});
		rdbtnContratado.setBounds(114, 108, 109, 23);
		add(rdbtnContratado);
		
		JRadioButton rdbtnExterno = new JRadioButton("Externo");
		rdbtnExterno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoEmpleado = "X";
				 textMail.setVisible(true);
				 lblMail.setVisible(true);
				 textUsuario.setText(txtDNI.getText());
					password = panel2.cleanString(cp2.CrearPass(nombres, apellidos));
					lblPw.setText(password);
					Buscar ldap = new Buscar();
					String user = txtDNI.getText();
					Buscar resultado = new Buscar();
					resultado = ldap.buscarUsuario(user);
					if( resultado.error == true) {
						lblNewLabel.setForeground(Color.red);
						lblNewLabel.setText("El usuario ya existe en AD");
						
					}
					else {
						lblNewLabel.setForeground(Color.blue);
						lblNewLabel.setText("Usuario OK");
					}
					usuario =panel2.cleanString(textUsuario.getText().toLowerCase());
			}
		});
		rdbtnExterno.setBounds(218, 108, 109, 23);
		add(rdbtnExterno);
		
		ButtonGroup tipoEmpleado = new ButtonGroup();
		tipoEmpleado.add(rdbtnEmpleado);
		tipoEmpleado.add(rdbtnContratado);
		tipoEmpleado.add(rdbtnExterno);
		
		if(rdbtnEmpleado.isSelected()==true)
		{
		 System.out.print("Seleccionó opción 1");
		}else if (rdbtnContratado.isSelected()==true) {
			 System.out.print("Seleccionó opción 2");
		}else if (rdbtnExterno.isSelected()==true) {
			 System.out.print("Seleccionó opción 3");
		}
		
		
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
					lblNewLabel.setForeground(Color.red);
					lblNewLabel.setText("El usuario ya existe en AD");
					
				}
				else {
					lblNewLabel.setForeground(Color.blue);
					lblNewLabel.setText("Usuario OK");
				}
			}
				usuario =panel2.cleanString(textUsuario.getText().toLowerCase());
			}
		});
		textUsuario.setBounds(84, 160, 86, 20);
		add(textUsuario);
		textUsuario.setColumns(10);
		
		
		textMail = new JTextField();
		textMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				email = panel2.cleanString(textMail.getText().toLowerCase());
			}
		});
		textMail.setBounds(84, 237, 342, 20);
		add(textMail);
		textMail.setColumns(10);
		
				

	}
}
