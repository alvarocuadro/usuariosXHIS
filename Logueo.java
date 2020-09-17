import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Logueo extends JFrame  {

	private JFrame frmLogin;
	private JTextField textUser;
	private JPasswordField passwordField;
	public static String userName;
	public static String contrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logueo window = new Logueo();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Logueo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 312, 188);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(32, 38, 83, 21);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setBounds(32, 80, 83, 21);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		textUser = new JTextField();
		textUser.setBounds(158, 38, 112, 20);
		frmLogin.getContentPane().add(textUser);
		textUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 80, 112, 20);
		frmLogin.getContentPane().add(passwordField);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				userName=textUser.getText();
				contrasenia=passwordField.getText();
				
				if(userName.equals("")||contrasenia.equals("")) {
					JOptionPane.showMessageDialog(null,"Debe completar los campos Usuario y Contraseña");
				}else {
				
				Ldap conexion= new Ldap();
				conexion.conectarLDAP();
					 try {
					        LdapContext ctx = new InitialLdapContext(conexion.env, null);
					        System.out.println("conectado a LDAP "+ctx);
					        
					        
							VentanaPrincipal ventana = new VentanaPrincipal();
							ventana.setVisible(true);
							frmLogin.setVisible(false);
				}
				catch (Exception e) {

				
					
					if(e.getMessage() != null )
				 {
						JOptionPane.showMessageDialog(null,"usuario y contraseña incorrectos");

					}					
					
				}
					
			}		
				
			}
		});
		btnIngresar.setBounds(118, 115, 89, 23);
		frmLogin.getContentPane().add(btnIngresar);
		
		JLabel lblNewLabel_2 = new JLabel("Credenciales de AD");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(79, 11, 130, 23);
		frmLogin.getContentPane().add(lblNewLabel_2);
	}
}
