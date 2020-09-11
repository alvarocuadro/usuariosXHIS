import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class GUI_LDAP {
	
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_LDAP window = new GUI_LDAP();
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
	public GUI_LDAP() {
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
				}
				
				DefaultTableModel dtm = new DefaultTableModel();
				
				dtm.setRowCount(0);		
				
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
				String query = "select a.nombre_corto, a.cod_perso, a.email, b.syslogin,e.codigo_categoria, e.nom_categ, c.sys_perfil_pk, d.sys_perfil_desc, g.codigo_servicio, g.servicio  from fpersona \r\n" + 
						"a inner join sys_usu b on a.codigo_personal = b.codigo_personal \r\n" + 
						"inner join sys_perfil_usu c on c.SYSLOGIN = b.syslogin \r\n" + 
						"inner join sys_perfil d on d.sys_perfil_pk = c.sys_perfil_pk \r\n" + 
						"inner join tcategor e on e.codigo_categoria = a.codigo_categoria\r\n" + 
						"inner join fpersona_servicio f on f.codigo_personal = a.codigo_personal \r\n" + 
						"inner join servicios g on g.codigo_servicio = f.codigo_servicio" + " where cod_perso = '"+dni+"'";
				
				try (Statement st = cn.createStatement();ResultSet rs=st.executeQuery(query);) {
										      
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
							rs.getString("servicio"),
							
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
					System.out.println(e+": no encuentra nada en el rs");
				} 				
				
			}
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
