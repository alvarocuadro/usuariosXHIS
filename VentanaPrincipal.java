import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.CardLayout;
import org.apache.commons.lang3.text.*;

public class VentanaPrincipal extends JFrame {

	JPanel contentPane;
	panel1 p1 = new panel1();
	panel2 p2 = new panel2();
	panel3 p3 = new panel3();
	
	
	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 575);
	
		getContentPane().add(p1);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnSalir.setBounds(565, 481, 89, 23);
		p1.add(btnSalir);
		
		JButton btnCrear = new JButton("Crear Usuario");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getContentPane().add(p2);
				p1.setVisible(false);
				p2.setVisible(true);
				p2.add(btnSalir);
				p2.txtDNI.setText(p1.campoDNI.getText());
				
				
				
				
			}
		});
		btnCrear.setBounds(405, 481, 134, 23);
		p1.add(btnCrear);
		p1.setVisible(true);
		
		JButton btnSgte = new JButton("Siguiente");
		p2.add(btnSgte);
		btnSgte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getContentPane().add(p3);
				p1.setVisible(false);
				p2.setVisible(false);
				p3.setVisible(true);
				p3.add(btnSalir);



}});
		btnSgte.setBounds(405, 481, 134, 23);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);
		
		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		menuArchivo.add(menuSalir);
		
		JMenu menuEditar = new JMenu("Editar");
		menuBar.add(menuEditar);
		
		JMenuItem menuCopiar = new JMenuItem("Copiar");
		menuEditar.add(menuCopiar);
		
		JMenuItem menuPegar = new JMenuItem("Pegar");
		menuEditar.add(menuPegar);
		
		JMenu menuAyuda = new JMenu("Ayuda");
		menuBar.add(menuAyuda);
		
		JMenuItem menuAcerca = new JMenuItem("Acerca de...");
		menuAyuda.add(menuAcerca);
		
	}
}
