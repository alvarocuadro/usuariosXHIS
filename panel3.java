import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class panel3 extends JPanel {
	private JTextField textMatricula;
	ClasesPanel3 clasespanel3 = new ClasesPanel3();
	private JTextField textField;
	private JTable tServicios;
	String serviciosFinales = "";
	public String nombres, apellidos, usuario, email, categoria, perfiles, matricula, password, tipoEmpleado, legajo, ccee, qx, prescribe, xfarma;	
	panel2 p2 = new panel2();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public panel3() {
		setLayout(null);
		
		
		JLabel lblMN = new JLabel("Matr\u00EDcula");
		lblMN.setBounds(441, 11, 86, 14);
		add(lblMN);
		
		textMatricula = new JTextField();
		textMatricula.setBounds(441, 36, 86, 20);
		add(textMatricula);
		textMatricula.setColumns(10);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboCategoria = new JComboBox();
		comboCategoria.setBounds(10, 36, 131, 20);
		comboCategoria.setModel(clasespanel3.getCategorias());
		add(comboCategoria);
		
		JLabel lblCat = new JLabel("Categor\u00EDa");
		lblCat.setBounds(10, 11, 131, 14);
		add(lblCat);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboPerfiles = new JComboBox();
		comboPerfiles.setBounds(161, 36, 270, 20);
		comboPerfiles.setModel(clasespanel3.getPerfiles());
		add(comboPerfiles);
		
		JLabel lblPerfil = new JLabel("Perfiles");
		lblPerfil.setBounds(161, 11, 46, 14);
		add(lblPerfil);
		
		JLabel lblServ = new JLabel("Servicios");
		lblServ.setBounds(10, 67, 94, 14);
		add(lblServ);
		
		JCheckBox chkCCEE = new JCheckBox("Hace CCEE");
		chkCCEE.setBounds(161, 87, 94, 23);
		add(chkCCEE);
		
		JCheckBox chkQx = new JCheckBox("Agenda Qx");
		chkQx.setBounds(278, 87, 94, 23);
		add(chkQx);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				Connection cn = null;
				cn = SQL.getConnection();

				
				DefaultTableModel dtm = new DefaultTableModel();
				dtm.addColumn("Código");
				dtm.addColumn("Servicio");
				dtm.addColumn("Área");
				String servicio = textField.getText();
				int letras = servicio.length();
		        if(letras >=3) { 
				
				Separar separa = new Separar();
				String queryServicio = separa.separar(servicio); 
				try {
					
					PreparedStatement pst=cn.prepareStatement(queryServicio);
					
					ResultSet rs=pst.executeQuery();
					while (rs.next()) {
						dtm.addRow(new Object[]{
							rs.getString("codigo_servicio"),
							rs.getString("servicio"),
							rs.getString("area")
						});
					}
										
					tServicios.setModel(dtm);
					tServicios.setAutoResizeMode(0);
					tServicios.getColumnModel().getColumn(0).setPreferredWidth(50);
					tServicios.getColumnModel().getColumn(1).setPreferredWidth(250);
					tServicios.getColumnModel().getColumn(2).setPreferredWidth(200);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
				
			}
		});
		textField.setBounds(10, 88, 131, 20);
		add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 123, 518, 223);
		add(scrollPane);
		
		tServicios = new JTable();
		scrollPane.setViewportView(tServicios);
		tServicios.setRowSelectionAllowed(true);
		tServicios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JButton btnGenUsuario = new JButton("Generar usuario");
		btnGenUsuario.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				

				int seleccionados = tServicios.getSelectedRowCount();
				if(seleccionados > 0) {	
if(seleccionados == 1) {
	int[] selectedrows = tServicios.getSelectedRows();
	serviciosFinales = tServicios.getValueAt(selectedrows[0], 0).toString();
		System.out.println(serviciosFinales);
		
		 

}


else {
	                 int[] selectedrows = tServicios.getSelectedRows();

	                 for (int i = 0; i < selectedrows.length; i++)
	                {

	                	 String getServicios = tServicios.getValueAt(selectedrows[i], 0).toString() + "|";
	                	 	                	 serviciosFinales += getServicios;
	                	 	                	 
	                }
serviciosFinales=serviciosFinales.substring(0 ,serviciosFinales.length() - 1);
}
if (chkCCEE.isSelected()==true) {ccee="1";}else {ccee="";}
if (chkQx.isSelected()==true) {qx="1";}else {qx="";}
legajo = panel2.legajo;
tipoEmpleado = panel2.tipoEmpleado;
password = panel2.password;
nombres = panel2.nombres;
apellidos = panel2.apellidos ;
email = panel2.email;
usuario = panel2.usuario;
categoria = clasespanel3.buscarPkCategoria(comboCategoria.getSelectedItem().toString());
perfiles = clasespanel3.buscarPkPerfil(comboPerfiles.getSelectedItem().toString());
matricula = textMatricula.getText();
if(categoria.equals("1")||categoria.equals("11")||categoria.equals("12")||categoria.equals("15")||categoria.equals("16")){prescribe="1";}else {prescribe="";}
if(perfiles.equals("21")||perfiles.equals("24")||perfiles.equals("25")||perfiles.equals("79")||perfiles.equals("132")||perfiles.equals("64")||perfiles.equals("67")||perfiles.equals("41")||perfiles.equals("63")) {xfarma = perfiles;}else {xfarma="";}
String script = "\n Nombres: "+nombres +"\n Apellidos: "+ apellidos +"\n Email: "+email +"\n Usuario: "+ usuario+"\n Password: "+password+"\n EmployeeType: "+tipoEmpleado+"\n Legajo: "+legajo+"\n extensionAttribute3: "+ matricula+
"\n extensionAttribute5: "+categoria+"\n extensionAttribute7: "+serviciosFinales+"\n extensionAttribute8: "+perfiles+"\n extensionAttribute9: "+ccee+"\n extensionAttribute10: "+qx+"\n extensionAttribute11: "+prescribe+"\n extensionAttribute12: "+xfarma;
JOptionPane.showMessageDialog(null, "Falta el script de creación de usuario en LDAP con las variables: "+script);
		
				}else {
	            	JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un servicio");
	            	
			}
		
				
			}
		});
		btnGenUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGenUsuario.setBounds(396, 366, 131, 37);
		add(btnGenUsuario);
		

		}
}

/*  
"EmployeeNumber" = [LEGAJO]
"EmployeeType" = [INTEXT]   
"employeeID" = [DNI]    
"mail" = [MAIL]
"extensionAttribute3" = [MATNAC]    
"extensionAttribute5" = [CATEGORIA]      
"extensionAttribute7" = [SERVICIOS]    
"extensionAttribute8" = [ROL]    
"extensionAttribute9" = [AGENDA_CEX] [varchar](10) NULL,		
"extensionAttribute10" = [AGENDA_QUIROF] [varchar] (10) NULL,    
"extensionAttribute11" = [PRESCRIPTOR] [varchar](10) NULL,
"extensionAttribute12" = xFARMA
*/
