import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;

public class GUI_Ldap {

	private JFrame frame;
	private JTextField campoDNI;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTable tablaSql;
	private JLabel lblNewLabel_2;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Ldap window = new GUI_Ldap();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_Ldap() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.setBounds(100, 100, 698, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		campoDNI = new JTextField();
		campoDNI.setBounds(22, 33, 137, 20);
		frame.getContentPane().add(campoDNI);
		campoDNI.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 122, 295, 131);
		frame.getContentPane().add(scrollPane);
		
		JTextPane textLDAP = new JTextPane();
		scrollPane.setViewportView(textLDAP);
		
		JButton btnBuscarDNI = new JButton("Buscar");
		btnBuscarDNI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				Connection cn = null;
				cn = SQL.getConnection();
				
				Buscar ldap = new Buscar();
				String dni = campoDNI.getText();
				Buscar resultado = new Buscar();
				resultado = ldap.listarUsuarios(dni);
				if( resultado.error == true) {
					System.out.println("No existe el usuario en GUI_LDAP.jar");
				textLDAP.setText("No existe el usuario en AD para ese DNI");
				}
				else {
				String[] listaResultado = new String[9];
				
				listaResultado[0]= resultado.usuario.toString();				
				listaResultado[1]= resultado.nombre.toString();
				listaResultado[2]= resultado.documento.toString();
				listaResultado[3]= resultado.email.toString();
				listaResultado[4]= resultado.categoria.toString();
				listaResultado[5]= resultado.perfil.toString();
				listaResultado[6]= resultado.servicio.toString();
				
				textLDAP.setText(listaResultado[0]+"\n"+listaResultado[1]+"\n"+listaResultado[2]+"\n"+listaResultado[3]+"\n"+listaResultado[4]+"\n"+listaResultado[5]+"\n"+listaResultado[6]);
				
				
				DefaultTableModel dtm = new DefaultTableModel();
				dtm.addColumn("Nombre");
				dtm.addColumn("DNI");
				dtm.addColumn("Email");
				dtm.addColumn("Usuario");
				dtm.addColumn("Cod. Categoría");
				dtm.addColumn("Categoría");
				dtm.addColumn("Cod. Perfiles");
				dtm.addColumn("Perfiles");
				dtm.addColumn("Cod. Servicio");
				dtm.addColumn("Servicio");
				String query = "SELECT campos From vista WHERE documento = '"+dni+"'";
				try {
					PreparedStatement pst=cn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					while (rs.next()) {
						dtm.addRow(new Object[]{
							rs.getString("nombre_corto"),
							rs.getString("cod_perso"),
							rs.getString("email"),
							rs.getString("syslogin"),
							rs.getString("codigo_categoria"),
							rs.getString("nom_categ"),
							rs.getString("sys_perfil_pk"),
							rs.getString("sys_perfil_desc"),
							rs.getString("codigo_servicio"),
							rs.getString("servicio")
						});
					}
										
					tablaSql.setModel(dtm);
					tablaSql.setAutoResizeMode(0);
					tablaSql.getColumnModel().getColumn(0).setPreferredWidth(150);
					tablaSql.getColumnModel().getColumn(1).setPreferredWidth(80);
					tablaSql.getColumnModel().getColumn(2).setPreferredWidth(150);
					tablaSql.getColumnModel().getColumn(3).setPreferredWidth(80);
					tablaSql.getColumnModel().getColumn(4).setPreferredWidth(50);
					tablaSql.getColumnModel().getColumn(5).setPreferredWidth(150);
					tablaSql.getColumnModel().getColumn(6).setPreferredWidth(50);
					tablaSql.getColumnModel().getColumn(7).setPreferredWidth(150);
					tablaSql.getColumnModel().getColumn(8).setPreferredWidth(50);
					tablaSql.getColumnModel().getColumn(9).setPreferredWidth(150);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}}
		});
		btnBuscarDNI.setBounds(192, 32, 89, 23);
		frame.getContentPane().add(btnBuscarDNI);
		
		lblNewLabel = new JLabel("DNI");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(22, 11, 52, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Active Directory");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(22, 85, 115, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 295, 650, 91);
		frame.getContentPane().add(scrollPane_1);
		
		tablaSql = new JTable();
		scrollPane_1.setViewportView(tablaSql);
		
		lblNewLabel_2 = new JLabel("xHIS");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(22, 264, 115, 20);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
