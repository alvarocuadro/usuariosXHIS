import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class panel1 extends JPanel {
	/**
	 * 
	 */

	public JTextField campoDNI;
	private JTable tablaSql;

	public panel1() {
		setLayout(null);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(10, 11, 46, 14);
		add(lblDNI);
		
		campoDNI = new JTextField();
		campoDNI.setBounds(10, 28, 125, 27);
		add(campoDNI);
		campoDNI.setColumns(10);
		
		JLabel lblAD = new JLabel("Active Directory");
		lblAD.setBounds(10, 79, 103, 27);
		add(lblAD);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 117, 326, 152);
		add(scrollPane);
		
		JTextPane textLDAP = new JTextPane();
		scrollPane.setViewportView(textLDAP);
		
		JLabel lblXhis = new JLabel("xHIS");
		lblXhis.setBounds(10, 290, 46, 14);
		add(lblXhis);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 315, 631, 143);
		add(scrollPane_1);
		
		tablaSql = new JTable();
		scrollPane_1.setViewportView(tablaSql);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				


				
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
					
					
				} catch (Exception e1) {
					System.out.println(e1+": no encuentra nada en el rs");
				} 				
				
				
				
				
				
				
			
			}
		});
		btnBuscar.setBounds(173, 30, 89, 23);
		add(btnBuscar);
		
}}


