import java.sql.DriverManager;
import java.sql.Connection;


public class SQL {
	
 private  static Connection cn;

 public  static Connection getConnection() {//conecta a la DB de SQL
	 
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				cn=DriverManager.getConnection("jdbc:sqlserver://Server:Puerto;databaseName=DB","usuario","contraseña");
				System.out.println("conectado");
			} catch (Exception e) {
				cn=null;
				System.out.println("no conectado");
			}
			return cn;}
	
}
