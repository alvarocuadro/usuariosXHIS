import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;

public class ClasesPanel3 extends SQL {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel getCategorias(){
		DefaultComboBoxModel modeloCategorias = new DefaultComboBoxModel();

			Connection cn = null;
			cn = SQL.getConnection();
		String query = "select nom_categ from tcategor WHERE codigo_categoria > 0 AND activo_sn = 1 and codigo_categoria <> 6";	
	
		try (Statement st = cn.createStatement();ResultSet rs=st.executeQuery(query);){
			while (rs.next()) {
				modeloCategorias.addElement(rs.getString(1));
} 
		
		}
		catch (Exception e) {
	System.out.println(e);
}
		return modeloCategorias;

}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel getPerfiles(){
		DefaultComboBoxModel modeloPerfiles = new DefaultComboBoxModel();

			Connection cn = null;
			cn = SQL.getConnection();
		String query = "select sys_perfil_desc from sys_perfil WHERE activo_sn = 1 AND sys_perfil_pk <>57 ORDER by sys_perfil_desc ASC";	
	
		try (Statement st = cn.createStatement();ResultSet rs=st.executeQuery(query);){
			while (rs.next()) {
				modeloPerfiles.addElement(rs.getString(1));
} 
		
		}
		catch (Exception e) {
	System.out.println(e);
}
		return modeloPerfiles;

}	
	
 public String buscarPkCategoria(String arg) {
	 String pk = "";
	 String cat = arg;
	 if (cat != "") {
		 Connection cn = null;
			cn = SQL.getConnection();
		String query = "select codigo_categoria from tcategor where nom_categ = '"+cat+"'";	
	System.out.println(query);
		try (Statement st = cn.createStatement();ResultSet rs=st.executeQuery(query);){
			while (rs.next()) {
				pk = rs.getString(1);
			}
		}
		catch (Exception e) {
	System.out.println(e);
}
	 }else {
		 System.out.println("la categoría vino vacía");
	 }
	 
	 
	 return pk; 
 }	
	
 public String buscarPkPerfil(String arg) {
	 String pk = "";
	 String per = arg;
	 if (per != "") {
		 Connection cn = null;
			cn = SQL.getConnection();
		String query = "select sys_perfil_pk from sys_perfil where sys_perfil_desc = '"+per+"'";	
	System.out.println(query);
		try (Statement st = cn.createStatement();ResultSet rs=st.executeQuery(query);){
			while (rs.next()) {
							pk = rs.getString(1); 
			}
		}
		catch (Exception e) {
	System.out.println(e);
}
		 
	 }else {
		 System.out.println("el perfil vino vacío");
	 }
	 
	 
	 return pk; 
 }	
 
	
}
